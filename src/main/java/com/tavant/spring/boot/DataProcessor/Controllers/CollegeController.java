package com.tavant.spring.boot.DataProcessor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tavant.spring.boot.DataProcessor.entities.College;
import com.tavant.spring.boot.DataProcessor.exceptions.ObjectsMalformedException;
import com.tavant.spring.boot.DataProcessor.exceptions.ResourcesNotFoundException;
import com.tavant.spring.boot.DataProcessor.services.CollegeService;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing College entities.
 */
@RestController
@RequestMapping("/api/colleges")
@CrossOrigin("http://localhost:4200")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    /**
     * Retrieve a list of all colleges.
     * 
     * @return ResponseEntity with a list of College objects and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<College>> getAllColleges() {
        List<College> colleges = collegeService.findAll();
        return new ResponseEntity<>(colleges, HttpStatus.OK);
    }

    /**
     * Retrieve a college by its ID.
     * 
     * @param id The ID of the college.
     * @return ResponseEntity with the College object and HTTP status.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<College> getCollegeById(@PathVariable int id) {
        try {
            Optional<College> college = collegeService.findCollegeById(id);
            return new ResponseEntity<>(college.orElse(null), HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieve a college by its name.
     * 
     * @param name The name of the college.
     * @return ResponseEntity with the College object and HTTP status.
     */
    @GetMapping("/{name}")
    public ResponseEntity<College> getCollegeByName(@PathVariable String name) {
        try {
            College college = collegeService.findCollegeByName(name);
            return new ResponseEntity<>(college, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create a new college.
     * 
     * @param college The College object to be created.
     * @return ResponseEntity with the created College object and HTTP status.
     */
    @PostMapping
    public ResponseEntity<College> createCollege(@RequestBody College college) {
        try {
            College createdCollege = collegeService.createCollege(college);
            return new ResponseEntity<>(createdCollege, HttpStatus.CREATED);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the name of a college with the given ID.
     * 
     * @param id The ID of the college.
     * @param name The new name of the college.
     * @return ResponseEntity with the number of updated rows and HTTP status.
     */
    @PutMapping("/name/{id}")
    public ResponseEntity<Integer> updateCollegeName(@PathVariable int id, @RequestParam String name) {
        try {
            int updatedCount = collegeService.updateCollegeName(id, name);
            return new ResponseEntity<>(updatedCount, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the address of a college with the given ID.
     * 
     * @param id The ID of the college.
     * @param address The new address of the college.
     * @return ResponseEntity with the number of updated rows and HTTP status.
     */
    @PutMapping("/address/{id}")
    public ResponseEntity<Integer> updateCollegeAddress(@PathVariable int id, @RequestParam String address) {
        try {
            int updatedCount = collegeService.updateCollegeAddress(id, address);
            return new ResponseEntity<>(updatedCount, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a college by its ID.
     * 
     * @param id The ID of the college.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollege(@PathVariable int id) {
        try {
            collegeService.deleteCollegeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
