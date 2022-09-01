package com.vivek.spring.data.jpa.tutorial.repository;


import com.vivek.spring.data.jpa.tutorial.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByFeeLessThan(double fee);
}
