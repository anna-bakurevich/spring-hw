package com.jd2.springhw.postprocessor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class MethodTimeBeanPostProcessor implements BeanPostProcessor {
    Map<String, Set<Method>> methodTimeMethods = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //находим все аннотированные методы и складываем их в map (название класса-бина - множество методов)
        for (Method declaredMethod : bean.getClass().getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(MethodTime.class)) {
                methodTimeMethods.compute(beanName, (k, v) -> {
                    if (v == null) {
                        v = new HashSet<>();
                    }
                    v.add(declaredMethod);
                    return v;
                });
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (methodTimeMethods.containsKey(beanName)) {
            final Set<Method> methods = methodTimeMethods.get(beanName);
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvice(new MethodInterceptor() {
                @Override
                public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                    final Method method = methodInvocation.getMethod();
                    if (methods.contains(method)) {
                        final LocalDateTime before = LocalDateTime.now();
                        Object result = method.invoke(bean, methodInvocation.getArguments());
                        final LocalDateTime after = LocalDateTime.now();
                        System.out.println("The method worked " + Duration.between(before, after).getSeconds() + " ms");
                        return result;
                    }
                    return method.invoke(bean, methodInvocation.getArguments());
                }
            });
            return proxyFactory.getProxy();
        }
        return bean;
    }
}
