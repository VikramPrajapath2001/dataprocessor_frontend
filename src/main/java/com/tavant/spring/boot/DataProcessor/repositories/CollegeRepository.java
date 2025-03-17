package com.tavant.spring.boot.DataProcessor.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tavant.spring.boot.DataProcessor.entities.College;

public interface CollegeRepository extends JpaRepository<College, Integer> {
	public College findByName(String name);
	
	@Modifying
	@Query("update College b set b.name = :name where b.Id = :id")
	public int updateCollegeName(@Param("id") int id, @Param("name") String name);
	
	@Modifying
	@Query("update College b set b.address = :address where b.Id = :id")
	public int updateCollegeAddress(@Param("id") int id, @Param("address") String address);
	
	public void deleteById(int id);
}
