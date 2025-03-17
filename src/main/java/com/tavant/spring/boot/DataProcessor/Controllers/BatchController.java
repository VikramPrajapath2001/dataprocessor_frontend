package com.tavant.spring.boot.DataProcessor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tavant.spring.boot.DataProcessor.entities.Batch;
import com.tavant.spring.boot.DataProcessor.exceptions.ObjectsMalformedException;
import com.tavant.spring.boot.DataProcessor.exceptions.ResourcesNotFoundException;
import com.tavant.spring.boot.DataProcessor.services.BatchService;
import java.util.List;

@RestController
@RequestMapping("/api/batches")
@CrossOrigin("http://localhost:4200")
public class BatchController {

    @Autowired
    private BatchService batchService;

    /**
     * Retrieve a batch by its id.
     * 
     * @param id The id of the batch.
     * @return ResponseEntity with the Batch object and HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Batch> getBatchById(@PathVariable int id) {
        try {
            Batch batch = batchService.findBatchById(id);
            return new ResponseEntity<>(batch, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Retrieve a list of all batches.
     * 
     * @return ResponseEntity with a list of Batch objects and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches() {
        List<Batch> batches = batchService.findAll();
        return new ResponseEntity<>(batches, HttpStatus.OK);
    }

    /**
     * Retrieve a list of batches by the staff member's name.
     * 
     * @param staffName The name of the staff member.
     * @return ResponseEntity with a list of Batch objects and HTTP status.
     */
    @GetMapping("/staff/{staffName}")
    public ResponseEntity<List<Batch>> getBatchesByStaffName(@PathVariable String staffName) {
        try {
            List<Batch> batches = batchService.findAllBatchesByStaffName(staffName);
            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the staff name of a batch with the given ID.
     * 
     * @param id The ID of the batch.
     * @param name The new staff name.
     * @return ResponseEntity with the number of updated rows and HTTP status.
     */
    @PutMapping("/staff/{id}")
    public ResponseEntity<Integer> updateBatchStaffName(@PathVariable int id, @RequestParam String name) {
        try {
            int updatedCount = batchService.updateBatchStaffName(id, name);
            return new ResponseEntity<>(updatedCount, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the capacity of a batch with the given ID.
     * 
     * @param id The ID of the batch.
     * @param capacity The new capacity.
     * @return ResponseEntity with the number of updated rows and HTTP status.
     */
    @PutMapping("/capacity/{id}")
    public ResponseEntity<Integer> updateBatchCapacity(@PathVariable int id, @RequestParam int capacity) {
        try {
            int updatedCount = batchService.updateBatchCapacity(id, capacity);
            return new ResponseEntity<>(updatedCount, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a batch by its ID.
     * 
     * @param id The ID of the batch.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable int id) {
        try {
            batchService.deleteBatchById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
