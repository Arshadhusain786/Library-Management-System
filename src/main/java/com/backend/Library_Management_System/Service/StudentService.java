package com.backend.Library_Management_System.Service;

import com.backend.Library_Management_System.DTO.StudentRequestDto;
import com.backend.Library_Management_System.DTO.StudentResponseDto;
import com.backend.Library_Management_System.DTO.StudentUpdateAgeRequestDto;
import com.backend.Library_Management_System.DTO.StudentUpdateEmailRequestDto;
import com.backend.Library_Management_System.Entity.LibraryCard;
import com.backend.Library_Management_System.Entity.Student;
import com.backend.Library_Management_System.Enum.CardStatus;
import com.backend.Library_Management_System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService
{

    @Autowired
     StudentRepository studentRepository;

    public void addStudent(StudentRequestDto studentRequestDto)
    {
        // Create a student object
        Student student = new Student();
        student.setAge(studentRequestDto.getAge());
        student.setName(studentRequestDto.getName());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setEmail(studentRequestDto.getEmail());

        //create a card object
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        // set card in student
        student.setCard(card);


        // now it will save both student and card
        studentRepository.save(student);

    }
    public List<Student>getStudents()
    {
        return studentRepository.findAll();
    }
    public String findByEmail(String email)
    {
        Student student = studentRepository.findByEmail(email);
        return student.getName();

    }
    public String deleteStudentByName(String name) {
        List<Student> students = studentRepository.findAll();
        boolean studentpresent = false;
        for (int i = 0; i < students.size(); i++) {
            Student obj = students.get(i);
            if (obj.getName().equals(name)) {
                int identity = obj.getId();
                studentRepository.deleteById(identity);
                studentpresent = true;
            }
        }
        if (!studentpresent)
            return "Student Does not Exist";
        else return "Student Removed Successfully";
    }
    public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto)
    {
        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());

        // update step..
        Student UpdatedStudent = studentRepository.save(student);

        // convert updated student into response DTO
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(UpdatedStudent.getId());
        studentResponseDto.setName(UpdatedStudent.getName());
        studentResponseDto.setEmail(UpdatedStudent.getEmail());

        return studentResponseDto;


    }
    public String updateStudentAge(StudentUpdateAgeRequestDto studentUpdateAgeRequestDto) throws Exception {

//        Student student = studentRepository.findById(studentUpdateAgeRequestDto.getId()).orElse(null);
//        if(student==null)
//        {
//            throw new Exception("Student does not exist");
//        }
//        else {
//            student.setAge(studentUpdateAgeRequestDto.getAge());
//
//            studentRepository.save(student);
//            return "Student Age Updated Successfully";
//        }

        Student student;

        try {
            student = studentRepository.findById(studentUpdateAgeRequestDto.getId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Student Doesn't Exist");
        }
        student.setAge(studentUpdateAgeRequestDto.getAge());

        studentRepository.save(student);
        return "Student Age Updated Successfully";

    }
}
