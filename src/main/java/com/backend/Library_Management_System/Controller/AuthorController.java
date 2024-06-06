package com.backend.Library_Management_System.Controller;

import com.backend.Library_Management_System.Entity.Author;
import com.backend.Library_Management_System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController
{
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author)
    {
       authorService.addAuthor(author);
       return "author added successfully";
    }

    @GetMapping("/get_authors")

    public List<Author> getAuthors()
    {
        return authorService.getAuthors();

    }
    @GetMapping("/get_mobile_number_by_id")
    public ResponseEntity getMobileNumberById(@RequestParam("id") int id)
    {
        try {
            String mobNo = authorService.getMobileNumberById(id);
            return new ResponseEntity<>(mobNo, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
