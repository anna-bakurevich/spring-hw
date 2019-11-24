package com.jd2.springhw.inject;

import com.jd2.springhw.beans.UserDao;
import com.jd2.springhw.postprocessor.MethodTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
инжект бина через сеттер
 */

@Service
public class InjectBySet {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
