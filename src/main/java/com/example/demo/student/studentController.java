package com.example.demo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.Arrays;

@RestController
@RequestMapping("api/v1/students")
public class studentController {

    private static final List<Student> STUDENTS    = Arrays.asList(
            new Student(1," Bhushan Navgire"),
            new Student(2, "Romit Zunjarrao"),
            new Student(3,"Tejas Kulkarni")
    );

    @GetMapping(path = "/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
            return STUDENTS.stream().filter(student -> studentId.equals(student.getStudentId()))
                    .findFirst()
                    .orElseThrow(()->new IllegalStateException("Student "+ studentId + " does not exist"));
    }
}
