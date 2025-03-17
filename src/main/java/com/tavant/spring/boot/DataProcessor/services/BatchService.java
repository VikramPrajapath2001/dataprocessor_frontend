package com.tavant.spring.boot.DataProcessor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tavant.spring.boot.DataProcessor.entities.Batch;
import com.tavant.spring.boot.DataProcessor.exceptions.ObjectsMalformedException;
import com.tavant.spring.boot.DataProcessor.exceptions.ResourcesNotFoundException;
import com.tavant.spring.boot.DataProcessor.repositories.BatchRepository;
import java.util.List;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    public Batch createBatch(Batch batch) throws ObjectsMalformedException {
        if (batch == null || batch.getName() == null || batch.getStaffName() == null) {
            throw new ObjectsMalformedException("Batch object not created properly. Cannot save.");
        }
        return batchRepository.save(batch);
    }
    
    public Batch findBatchById(int id) throws ResourcesNotFoundException {
        return batchRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Batch not found"));
    }
    public List<Batch> findAll() {
        return batchRepository.findAll();
    }
        
    public Batch findBatchByName(String name) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (name == null || name.isEmpty()) {
            throw new ObjectsMalformedException("Batch name is null or empty. Cannot retrieve batch.");
        }
        Batch batch = batchRepository.findByName(name);
        if (batch == null) {
            throw new ResourcesNotFoundException("Batch not found with name: " + name);
        }
        return batch;
    }

    public List<Batch> findAllBatchesByStaffName(String staffName) throws ObjectsMalformedException {
        if (staffName == null || staffName.isEmpty()) {
            throw new ObjectsMalformedException("Staff name is null or empty. Cannot retrieve batches.");
        }
        return batchRepository.findAllByStaffName(staffName);
    }

    @Transactional
    public int updateBatchStaffName(int id, String name) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (name == null || name.isEmpty()) {
            throw new ObjectsMalformedException("Staff name is null or empty. Cannot update.");
        }
        Batch batch = batchRepository.findById(id).orElse(null);
        if (batch == null) {
            throw new ResourcesNotFoundException("Batch not found with ID: " + id);
        }
        return batchRepository.updateBatchName(id, name);
    }

    @Transactional
    public int updateBatchCapacity(int id, int capacity) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (capacity <= 0) {
            throw new ObjectsMalformedException("Batch capacity is invalid. Cannot update.");
        }
        Batch batch = batchRepository.findById(id).orElse(null);
        if (batch == null) {
            throw new ResourcesNotFoundException("Batch not found with ID: " + id);
        }
        return batchRepository.updateBatchCapacity(id, capacity);
    }

    public void deleteBatchById(int id) throws ResourcesNotFoundException {
        Batch batch = batchRepository.findById(id).orElse(null);
        if (batch == null) {
            throw new ResourcesNotFoundException("Batch not found with ID: " + id);
        }
        batchRepository.deleteById(id);
    }
}
