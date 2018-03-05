package com.wisely.web;

import com.wisely.dao.StudentRepository;
import com.wisely.domain.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
	//1 Spring Data JPA已自动为你注册bean，所以可自动注入
	@Autowired
	StudentRepository personRepository;
	
	/**
	 * 保存
	 * save支持批量保存：<S extends T> Iterable<S> save(Iterable<S> entities);
	 * 
	 * 删除：
	 * 删除支持使用id，对象以，批量删除及删除全部：
	 * void delete(ID id);
	 * void delete(T entity);
	 * void delete(Iterable<? extends T> entities);
	 * void deleteAll();
	 * 
	 */
	@RequestMapping("/save")
	public Student save(String name, String address, Integer age){
		
		Student p = personRepository.save(new Student(null, name, age, address));
		
		return p;
		
	}
	

	
	/**
	 * 测试findByAddress
	 */
	@RequestMapping("/q1")
	public List<Student> q1(String address){
		
		List<Student> people = personRepository.findByAddress(address);
		
		return people;
		
	}
	
	/**
	 * 测试findByNameAndAddress
	 */
	@RequestMapping("/q2")
	public Student q2(String name, String address){
		
		Student people = personRepository.findByNameAndAddress(name, address);
		
		return people;
		
	}
	
	/**
	 * 测试withNameAndAddressQuery
	 */
	@RequestMapping("/q3")
	public Student q3(String name, String address){

		Student p = personRepository.withNameAndAddressQuery(name, address);

		return p;

	}

	/**
	 * 测试withNameAndAddressNamedQuery
	 */
	@RequestMapping("/q4")
	public Student q4(String name, String address){

		Student p = personRepository.withNameAndAddressNamedQuery(name, address);

		return p;

	}
	
	/**
	 * 测试排序
	 */
	@RequestMapping("/sort")
	public List<Student> sort(){
		
		List<Student> people = personRepository.findAll(new Sort(Direction.ASC,"age"));
		
		return people;
		
	}
	
	/**
	 * 测试分页
	 */
	@RequestMapping("/page")
	public Page<Student> page(){
		
		Page<Student> pagePeople = personRepository.findAll(new PageRequest(1, 2));
		
		return pagePeople;
		
	}
	
	
//	@RequestMapping("/auto")
//	public Page<Student> auto(Student person){
//
//		Page<Student> pagePeople = personRepository.findByAuto(person, new PageRequest(0, 10));
//
//		return pagePeople;
//
//	}
	
	

}
