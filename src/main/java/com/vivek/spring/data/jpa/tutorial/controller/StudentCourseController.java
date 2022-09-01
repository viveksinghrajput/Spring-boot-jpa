package com.vivek.spring.data.jpa.tutorial.controller;


import com.vivek.spring.data.jpa.tutorial.entity.Course;
import com.vivek.spring.data.jpa.tutorial.entity.Student;
import com.vivek.spring.data.jpa.tutorial.repository.CourseRepository;
import com.vivek.spring.data.jpa.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {
@Autowired
    private StudentRepository studentRepository;
@Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public Student saveStudentWithCourse(@RequestBody Student student){
      return   studentRepository.save(student);
    }

    @GetMapping
    public List<Student> findALlStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public Student findStudent(@PathVariable Long studentId){
        return studentRepository.findById(studentId).orElse(null);
    }
    @GetMapping("/find/{name}")
    public List<Student> findStudentsContainingByName(@PathVariable String name){
        return studentRepository.findByNameContaining(name);
    }

    @GetMapping("/search/{price}")
    public List<Course> findCourseLessThanPrice(@PathVariable double price){
        return courseRepository.findByFeeLessThan(price);
    }

}
