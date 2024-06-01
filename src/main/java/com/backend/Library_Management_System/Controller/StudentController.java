package com.backend.Library_Management_System.Controller;

import com.backend.Library_Management_System.Entity.Student;
import com.backend.Library_Management_System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "Student has been added successfully";
    }
    @GetMapping("/get_students")
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }
}
