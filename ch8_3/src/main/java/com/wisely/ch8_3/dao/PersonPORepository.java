package com.wisely.ch8_3.dao;

import com.wisely.ch8_3.domain.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

//@RepositoryRestResource(path = "people")
public interface PersonPORepository extends JpaRepository<Person, Long> {
	
	@RestResource(path = "nameStartsWith", rel = "nameStartsWith")
	Person findByNameStartsWith(@Param("name")String name);

}
