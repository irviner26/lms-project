package com.smkr.lms.Controller;

import com.smkr.lms.Model.Response.CourseResponse;
import com.smkr.lms.Model.Response.WebResponse;
import com.smkr.lms.Service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping(
            path = "/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CourseResponse> getCourse(@PathVariable("code") String code) {
        CourseResponse courseResponse = courseService.getByCourseCode(code);
        return WebResponse.<CourseResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(courseResponse)
                .error(null)
                .build();
    };

    @GetMapping(
            path = "/teacher/{teacher}/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<CourseResponse>> coursesOfTeacher(@PathVariable("teacher") String teacher,
                                                              @PathVariable("page") int noPage) {
        List<CourseResponse> courseResponses = courseService
                .getByTeacherSearch(teacher,noPage,10);
        return WebResponse.<List<CourseResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(courseResponses)
                .error(null)
                .build();
    }
}
