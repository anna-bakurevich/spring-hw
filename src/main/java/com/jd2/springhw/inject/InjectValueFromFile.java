package com.jd2.springhw.inject;

import com.jd2.springhw.postprocessor.MethodTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
инжект значения из файла value.properties
 */

@Component
public class InjectValueFromFile {
    @Value("${injectValueFromFile.name}")
    private String name;
    @Value("${injectValueFromFile.number}")
    private int number;

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }


}
