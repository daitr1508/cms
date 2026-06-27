package com.lms.cms.controller;

import com.lms.cms.dto.CourseRequestDTO;
import com.lms.cms.dto.CourseResponseDTO;
import com.lms.cms.service.CourseService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO course) {
        CourseResponseDTO createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCoursePartially(@PathVariable Long id, @RequestBody CourseRequestDTO requestDTO) {
        return ResponseEntity.ok(courseService.updateCoursePartially(id, requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourseCompletely(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO requestDTO) {
        return ResponseEntity.ok(courseService.updateCourseCompletely(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
