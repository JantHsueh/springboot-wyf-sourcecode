package com.wisely.dao;

import com.wisely.domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//public interface  StudentRepository extends CustomRepository<Student, Long> {
public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByAddress(String address);
	
	Student findByNameAndAddress(String name, String address);
	
	@Query("select p from Student p where p.name= :name and p.address= :address")
	Student withNameAndAddressQuery(@Param("name")String name, @Param("address")String address);

	Student withNameAndAddressNamedQuery(String name, String address);

}
