package com.smkr.lms.Repository;

import com.smkr.lms.Model.Entity.Course;
import com.smkr.lms.Model.Enums.Status;
import com.smkr.lms.Model.Entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    List<User> findByStatus(Status status, Pageable pageable);
    List<User> findByCourses(Course course, Pageable pageable);
}
