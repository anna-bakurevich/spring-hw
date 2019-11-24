package com.jd2.springhw.inject;

import com.jd2.springhw.beans.UserDao;
import com.jd2.springhw.postprocessor.MethodTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
инжект бина через поле
 */

@Service
public class InjectByField {

    //Field injection is not recommended
    @Autowired
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }
}
