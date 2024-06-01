package com.backend.Library_Management_System.Service;

import com.backend.Library_Management_System.Entity.LibraryCard;
import com.backend.Library_Management_System.Entity.Student;
import com.backend.Library_Management_System.Enum.CardStatus;
import com.backend.Library_Management_System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
     StudentRepository studentRepository;

    public void addStudent(Student student)
    {
        // set the value of card
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setValidTill("03/01/2005");
        card.setStudent(student);

        // set the card attribute in student

        student.setCard(card);

        studentRepository.save(student);
    }
    public List<Student>getStudents()
    {
        return studentRepository.findAll();
    }
}
