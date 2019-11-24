package com.jd2.springhw.inject;

import com.jd2.springhw.beans.UserDao;
import org.springframework.stereotype.Service;

/*
инжект бина через конструктор
 */

@Service
public class InjectByConstructor {
    private final UserDao userDao;

    //    @Autowired можно не использовать, т.к. конструктор единственный
    public InjectByConstructor(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
