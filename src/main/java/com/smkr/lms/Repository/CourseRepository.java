package com.smkr.lms.Repository;

import com.smkr.lms.Model.Entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);
    List<Course> findByTeacherContainingIgnoreCase(String teacher, Pageable pageable);
    List<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
