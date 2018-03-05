package com.xuexuan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //声明为配置类
@EnableConfigurationProperties (HelloServiceProperties.class)// 开启属性注入，以EnableConfigurationProperties声明，使用@autowired注入
@ConditionalOnClass(HelloService.class)//当helloService在类路径的条件下
@ConditionalOnProperty(prefix = "hello",value = "enabled",matchIfMissing = true)
public  class HelloServiceAutoConfiguration {

    @Autowired
    private HelloServiceProperties helloServiceProperties;

    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService(){
        HelloService helloService = new HelloService();
        helloService.setMsg(helloServiceProperties.getMsg());
        return helloService;
    }
}
