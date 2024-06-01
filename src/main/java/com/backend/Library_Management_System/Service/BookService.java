package com.backend.Library_Management_System.Service;


import com.backend.Library_Management_System.Entity.Author;
import com.backend.Library_Management_System.Entity.Book;
import com.backend.Library_Management_System.Repository.AuthorRepository;
import com.backend.Library_Management_System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService
{
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(Book book) throws Exception
    {
        Author author;
        try {
            author = authorRepository.findById(book.getAuthor().getId()).get();
            //.get() will return the value if it is present otherwise it
            //will throw the exception
        }
        catch(Exception e)
        {
            return "Author not present";
        }
        List<Book> booksWritten=author.getBooks();
        booksWritten.add(book); // adding the books that i havw written in my list
        authorRepository.save(author);
        return "Book added";

    }
    public List<Book> getBooks()
    {
        return bookRepository.findAll();
    }

}
