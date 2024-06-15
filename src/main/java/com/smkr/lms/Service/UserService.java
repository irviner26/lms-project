package com.smkr.lms.Service;

import com.smkr.lms.Model.Response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserByUserName(String username);
    List<UserResponse> getUsersByStatus(String status, int noPage, int noContent);
    List<UserResponse> getUsersByCourse(String course, int noPage, int noContent);
}
