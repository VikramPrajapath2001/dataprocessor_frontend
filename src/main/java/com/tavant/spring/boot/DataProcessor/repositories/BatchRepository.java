package com.tavant.spring.boot.DataProcessor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tavant.spring.boot.DataProcessor.entities.Batch;
import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
    public Batch findByName(String name);

    public List<Batch> findAllByStaffName(String staffName);

    @Modifying
    @Query("update Batch b set b.staffName = :name where b.id = :id")
    public int updateBatchName(@Param("id") int id, @Param("name") String name);

    @Modifying
    @Query("update Batch b set b.capacity = :capacity where b.id = :id")
    public int updateBatchCapacity(@Param("id") int id, @Param("capacity") int capacity);

    public void deleteById(int id);
}
