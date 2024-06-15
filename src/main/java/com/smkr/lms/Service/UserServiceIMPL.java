package com.smkr.lms.Service;

import com.smkr.lms.Model.Entity.User;
import com.smkr.lms.Model.Enums.Status;
import com.smkr.lms.Model.Response.UserResponse;
import com.smkr.lms.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceIMPL implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponse getUserByUserName(String username) {
        return mapToUsResp(
                userRepository
                        .findByUsername(username)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "User not found"))
        );
    }

    @Override
    public List<UserResponse> getUsersByStatus(String status, int noPage, int noContent) {
        Pageable pageReq = PageRequest.of(noPage, noContent);
        List<User> userList = userRepository.findByStatus(Status.valueOf(status), pageReq);
        return userList
                .stream()
                .map(UserServiceIMPL::mapToUsResp)
                .toList();
    }

    @Override
    public List<UserResponse> getUsersByCourse(String course, int noPage, int noContent) {
        return List.of();
    }

    public static UserResponse mapToUsResp(User user) {
        return UserResponse.builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .status(user.getStatus().name())
                .build();
    }
}
