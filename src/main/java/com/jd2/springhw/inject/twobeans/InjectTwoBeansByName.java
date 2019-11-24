package com.jd2.springhw.inject.twobeans;

import org.springframework.stereotype.Service;

/*
инжект двух бинов одного интерфейса по имени
 */
@Service
public class InjectTwoBeansByName {

    private final Inter firstBean;
    private final Inter secondBean;

    public InjectTwoBeansByName(Inter firstBean, Inter secondBean) {
        this.firstBean = firstBean;
        this.secondBean = secondBean;
    }

    public Inter getFirstBean() {
        return firstBean;
    }

    public Inter getSecondBean() {
        return secondBean;
    }
}
