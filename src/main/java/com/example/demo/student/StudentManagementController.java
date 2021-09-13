package com.example.demo.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS    = Arrays.asList(
            new Student(1," Bhushan Navgire"),
            new Student(2, "Romit Zunjarrao"),
            new Student(3,"Tejas Kulkarni")
    );

    @GetMapping
    public List<Student> getAllStudents(){
        System.out.println("getAllStudents");
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("regsiterNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("deleteNewStudent");
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println("updateNewStudent");
        System.out.println(String.format("%s %s", student, studentId));
    }
}
