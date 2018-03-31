package com.wisely.ch8_3.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.DefaultFormattingConversionService;

import javax.annotation.Resource;

/**
 * create br xuexuan
 * time 2018-03-2018/3/9  17
 */
@Configuration
public class MyConversionService {

    @Resource
    TestConverter mTestConverter;


    @Bean(name="ConversionService")
    public DefaultFormattingConversionService getConversionService() {
        DefaultFormattingConversionService bean = new DefaultFormattingConversionService();
//        Set<Converter> converters = new HashSet<>();
//        converters.add(new TestConverter());
        bean.addConverter(mTestConverter);
//        bean.se(converters);
        return bean;
    }



}
