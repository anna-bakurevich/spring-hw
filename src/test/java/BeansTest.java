import com.jd2.springhw.JavaConfig;
import com.jd2.springhw.beans.DefaultUserDao;
import com.jd2.springhw.beans.UserDao;
import com.jd2.springhw.inject.*;
import com.jd2.springhw.inject.list.InjectListByConstructor;
import com.jd2.springhw.inject.twobeans.InjectTwoBeansByName;
import com.jd2.springhw.postprocessor.TestPostProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class BeansTest {

    //1. Контекст с одим бином с помощью xml
    @Test
    void xmlContextTest() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("context.xml");
        context.refresh();

        UserDao userDao = context.getBean(UserDao.class);
        assertNotNull(userDao);
        System.out.println(userDao.toString());

    }

    //2. Контекст с одним бином с помощью java config
    @Test
    void javaConfigTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(JavaConfig.class);
        context.refresh();

        UserDao userDao = context.getBean(UserDao.class);
        assertNotNull(userDao);
        System.out.println(userDao.toString());
    }

    //3. Контекст с одним бином с помощью аннотаций
    @Test
    void annotationConfigTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.jd2.springhw.beans");
        context.refresh();

        UserDao userDao = context.getBean(UserDao.class);
        assertNotNull(userDao);
        System.out.println(userDao.toString());
    }

    //4. Инжект бина через поле
    @Test
    void injectByFieldTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(JavaConfig.class);
        context.register(InjectByField.class);
        context.refresh();

        InjectByField bean = context.getBean(InjectByField.class);

        assertNotNull(bean);
        assertNotNull(bean.getUserDao());
        System.out.println(bean);
        System.out.println(bean.getUserDao());

    }

    //5. Инжект бина через сеттер
    @Test
    void injectBySetTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(JavaConfig.class);
        context.register(InjectBySet.class);
        context.refresh();

        InjectBySet bean = context.getBean(InjectBySet.class);

        assertNotNull(bean);
        assertNotNull(bean.getUserDao());
        System.out.println(bean);
        System.out.println(bean.getUserDao());
    }

    //6. Инжект бина через конструктор
    @Test
    void injectByConstructorTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(JavaConfig.class);
        context.register(InjectByConstructor.class);
        context.refresh();

        InjectByConstructor bean = context.getBean(InjectByConstructor.class);

        assertNotNull(bean);
        assertNotNull(bean.getUserDao());
        System.out.println(bean);
        System.out.println(bean.getUserDao());
    }

    //7. Инжект двух бинов одного интерфейса по имени
    @Test
    void injectTwoBeansByNameTest() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "com.jd2.springhw.inject.twobeans");

        final InjectTwoBeansByName bean = context.getBean(InjectTwoBeansByName.class);

        assertNotNull(bean.getFirstBean());
        assertNotNull(bean.getSecondBean());
        assertNotEquals(bean.getFirstBean(), bean.getSecondBean());

    }

    //8. Инжект списка бинов через конструктор
    @Test
    void InjectListByConstructor() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "com.jd2.springhw.inject.list");

        final InjectListByConstructor bean = context.getBean(InjectListByConstructor.class);
        assertNotNull(bean.getTasks());
        assertFalse(bean.getTasks().isEmpty());
        assertEquals(2, bean.getTasks().size());

    }

    //9. Инжект значений из файла проперти
    @Test
    void injectValueFromFileTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("contextValue.xml");

        InjectValueFromFile bean = context.getBean(InjectValueFromFile.class);

        assertEquals(1, bean.getNumber());
        assertEquals("Anna", bean.getName());

        System.out.println(bean.getNumber());
        System.out.println(bean.getName());
    }

    //10. Создать свой бинпостпроцессор, который замеряет время работы метода, помеченного аннотацией
    @Test
    void myBeanPostProcessorTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "com.jd2.springhw.postprocessor");

        final TestPostProcessor bean = context.getBean(TestPostProcessor.class);
        assertNotNull(bean);
        bean.print();
        bean.printWithoutAnnotation();

    }
}
