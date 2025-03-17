package com.tavant.spring.boot.DataProcessor.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavant.spring.boot.DataProcessor.entities.College;
import com.tavant.spring.boot.DataProcessor.exceptions.ObjectsMalformedException;
import com.tavant.spring.boot.DataProcessor.services.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private CollegeService collegeService;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws ObjectsMalformedException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Parse the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            College college = objectMapper.readValue(file.getInputStream(), College.class);

            // Save the college and its associated entities to the database
            collegeService.createCollege(college);

            return ResponseEntity.ok("File uploaded and data saved successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to process file: " + e.getMessage());
        }
    }
}
