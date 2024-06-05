package com.backend.Library_Management_System.Controller;

import com.backend.Library_Management_System.DTO.StudentRequestDto;
import com.backend.Library_Management_System.DTO.StudentResponseDto;
import com.backend.Library_Management_System.DTO.StudentUpdateEmailRequestDto;
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
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto) {
        studentService.addStudent(studentRequestDto);
        return "Student has been added successfully";
    }
    @GetMapping("/get_students")
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }
    @GetMapping("/find_by_email")
    public String findStudentByEmail(@RequestParam("email") String email)
    {
        return studentService.findByEmail(email);
    }
    @DeleteMapping("/delete_student_by_name")
    public String deleteStudentByName(@RequestParam("name") String name)
    {
        return studentService.deleteStudentByName(name);
    }
    @PutMapping("/update_email")
    public StudentResponseDto updateEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        return studentService.updateEmail(studentUpdateEmailRequestDto);
    }

}
