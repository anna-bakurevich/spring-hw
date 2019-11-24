package com.jd2.springhw.beans;

import com.jd2.springhw.model.User;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserDao implements UserDao {

    @Override
    public int saveUser(User user) {
        System.out.println("User saved");
        return 1;
    }


    @Override
    public String toString() {
        return "Class DefaultUserDao";
    }
}
