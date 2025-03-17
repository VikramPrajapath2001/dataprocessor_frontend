package com.tavant.spring.boot.DataProcessor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tavant.spring.boot.DataProcessor.entities.Batch;
import com.tavant.spring.boot.DataProcessor.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findById(int id);
	public List<Student> findAllByBatch(Batch batch);
	
	@Modifying
	@Query("update Student b set b.name = :name where b.Id = :id")
	public int updateStudentName(@Param("id") int id, @Param("name") String name);
	
	public void deleteById(int id);
}
