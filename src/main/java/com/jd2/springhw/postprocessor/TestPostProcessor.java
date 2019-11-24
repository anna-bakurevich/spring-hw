package com.jd2.springhw.postprocessor;

import org.springframework.stereotype.Service;

@Service
public class TestPostProcessor{

    @MethodTime
    public void print() {
        try {
            Thread.sleep(1000);
            System.out.println("===================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printWithoutAnnotation()
    {
        System.out.println("********************");
    }
}
