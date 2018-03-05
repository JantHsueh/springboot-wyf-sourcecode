package com.wisely;

import com.wisely.dao.StudentRepository;
import com.wisely.support.CustomRepositoryFactoryBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class Ch82Application {
	@Autowired
    StudentRepository studentRepository;
	
	
    public static void main(String[] args) {
        SpringApplication.run(Ch82Application.class, args);
        
    }
    
 
}
