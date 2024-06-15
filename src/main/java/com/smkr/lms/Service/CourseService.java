package com.smkr.lms.Service;

import com.smkr.lms.Model.Response.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseResponse getByCourseCode(String code);
    List<CourseResponse> getByTeacherSearch(String teacher, int noPage, int noContent);
    List<CourseResponse> getByNameSearch(String name, int noPage, int noContent);
}
