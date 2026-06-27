package com.lms.cms.dto;

import com.lms.cms.entity.Course;

public record CourseResponseDTO(
        Long id,
        String name,
        String description,
        Integer price
) {
    public static CourseResponseDTO from(Course course) {
        return new CourseResponseDTO(course.getId(), course.getName(), course.getDescription(), course.getPrice());
    }
}
