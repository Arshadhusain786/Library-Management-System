package com.backend.Library_Management_System.Controller;

import com.backend.Library_Management_System.Entity.Author;
import com.backend.Library_Management_System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
