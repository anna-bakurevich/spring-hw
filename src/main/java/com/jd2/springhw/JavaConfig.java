package com.jd2.springhw;

import com.jd2.springhw.beans.DefaultUserDao;
import com.jd2.springhw.beans.DefaultUserService;
import com.jd2.springhw.beans.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public UserDao getUserDao() {
        return new DefaultUserDao();
    }


    @Bean
    public DefaultUserService getDefaultUserDao(UserDao userDao) {
        return new DefaultUserService(userDao);
    }
}
