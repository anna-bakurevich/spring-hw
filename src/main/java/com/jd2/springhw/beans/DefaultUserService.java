package com.jd2.springhw.beans;

import com.jd2.springhw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserService implements UserService {

    private UserDao userDao;

    public DefaultUserService() {
    }

    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int save(User user) {
        return userDao.saveUser(user);
    }
}
