package com.wisely.ch8_3.dao;

import com.wisely.ch8_3.domain.Person;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * create br xuexuan
 * time 2018-03-2018/3/10  10
 */
@Component
public  class TestConverter implements Converter<Person, Person> {
    public Person convert(Person source) {
        Person lPerson = new Person();
        lPerson.setAddress("123");
        lPerson.setName("123456");

        return lPerson;
    }

}
