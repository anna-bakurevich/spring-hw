package com.jd2.springhw.inject.list;

import org.springframework.stereotype.Service;

import java.util.List;

/*
инжект списка через конструктор
 */
@Service
public class InjectListByConstructor {
    private final List<InterList> tasks;

    public InjectListByConstructor(List<InterList> tasks) {
        this.tasks = tasks;
    }

    public List<InterList> getTasks() {
        return tasks;
    }
}
