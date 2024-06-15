package com.smkr.lms.Service;

import com.smkr.lms.Model.Entity.Course;
import com.smkr.lms.Model.Response.CourseResponse;
import com.smkr.lms.Repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class CourseServiceIMPL implements CourseService{

    private final CourseRepository courseRepository;

    @Override
    public CourseResponse getByCourseCode(String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "User not found"));
        return mapToCouRes(course);
    }

    @Override
    public List<CourseResponse> getByTeacherSearch(String teacher, int noPage, int noContent) {
        List<Course> courseList = courseRepository
                .findByTeacherContainingIgnoreCase(teacher, PageRequest.of(noPage, noContent));
        return courseList
                .stream()
                .map(CourseServiceIMPL::mapToCouRes)
                .toList();
    }

    @Override
    public List<CourseResponse> getByNameSearch(String name, int noPage, int noContent) {
        List<Course> courseList = courseRepository
                .findByNameContainingIgnoreCase(name, PageRequest.of(noPage, noContent));
        return courseList
                .stream()
                .map(CourseServiceIMPL::mapToCouRes)
                .toList();
    }

    public static CourseResponse mapToCouRes(Course course) {
        return CourseResponse.builder()
                .name(course.getName())
                .code(course.getCode())
                .description(course.getDescription())
                .teacher(course.getTeacher())
                .build();
    }
}
