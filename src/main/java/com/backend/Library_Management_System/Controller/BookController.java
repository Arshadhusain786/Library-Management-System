package com.backend.Library_Management_System.Controller;

import com.backend.Library_Management_System.DTO.BookRequestDto;
import com.backend.Library_Management_System.DTO.BookResponseDto;
import com.backend.Library_Management_System.Entity.Book;
import com.backend.Library_Management_System.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController
{
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public BookResponseDto addBook(@RequestBody BookRequestDto bookRequestDto) throws Exception {
        return bookService.addBook(bookRequestDto);
    }

    @GetMapping("/get_books")
    public List<Book> getBooks()
    {
        return bookService.getBooks();
    }

}
