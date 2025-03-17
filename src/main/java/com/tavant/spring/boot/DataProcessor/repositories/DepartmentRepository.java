package com.tavant.spring.boot.DataProcessor.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tavant.spring.boot.DataProcessor.entities.Department;
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    public Department findByNameAndCollegeId(String name, Long collegeId);
    
    @Modifying
	@Query("update Department b set b.name = :name where b.Id = :id")
	public int updateDepartmentName(@Param("id") int id, @Param("name") String name);
	
	
	@Modifying
	@Query("update Department b set b.hod = :hod where b.Id = :id")
	public int updateDepartmentHod(@Param("id") int id, @Param("hod") String hod);
	
	public void deleteById(int id);
    
}
