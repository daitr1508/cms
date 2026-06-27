package com.lms.cms.service;

import com.lms.cms.dto.CourseRequestDTO;
import com.lms.cms.dto.CourseResponseDTO;
import com.lms.cms.entity.Course;
import com.lms.cms.exception.CourseNotFoundException;
import com.lms.cms.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        return CourseResponseDTO.from(courseRepository.save(mapToEntity(dto)));
    }

    public CourseResponseDTO getCourseById(Long id) {
        return CourseResponseDTO.from(
                courseRepository.findById(id).orElseThrow(() ->
                        new CourseNotFoundException("Course not found with id:" + id))
        );
    }

    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(CourseResponseDTO::from).toList();
    }

    public CourseResponseDTO updateCoursePartially(Long id, CourseRequestDTO dto) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new CourseNotFoundException("Course not found with id:" + id));
        if (dto.name() != null) course.setName(dto.name());
        if (dto.description() != null) course.setDescription(dto.description());
        if (dto.price() != null) course.setPrice(dto.price());
        return CourseResponseDTO.from(courseRepository.save(course));
    }

    public CourseResponseDTO updateCourseCompletely(Long id, CourseRequestDTO dto) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new CourseNotFoundException("Course not found with id:" + id));
        course.setName(dto.name());
        course.setDescription(dto.description());
        course.setPrice(dto.price());
        return CourseResponseDTO.from(courseRepository.save(course));
    }

    public String deleteCourse(Long id) {
        courseRepository.findById(id).orElseThrow(() ->
                new CourseNotFoundException("Course not found with id:" + id));
        courseRepository.deleteById(id);
        return "Course deleted successfully";
    }

    private Course mapToEntity(CourseRequestDTO dto) {
        Course course = new Course();
        course.setName(dto.name());
        course.setDescription(dto.description());
        course.setPrice(dto.price());
        return course;
    }
}
