package com.backend.Library_Management_System.Service;

import com.backend.Library_Management_System.DTO.BookRequestDto;
import com.backend.Library_Management_System.DTO.BookResponseDto;
import com.backend.Library_Management_System.Entity.Author;
import com.backend.Library_Management_System.Entity.Book;
import com.backend.Library_Management_System.Repository.AuthorRepository;
import com.backend.Library_Management_System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
     BookRepository bookRepository;
    @Autowired
     AuthorRepository authorRepository;

    public BookResponseDto addBook(BookRequestDto bookRequestDto) throws Exception {

        // get the author object
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();

        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setGenre(bookRequestDto.getGenre());
        book.setPrice(bookRequestDto.getPrice());
        book.setIssued(false);
        book.setAuthor(author);

        author.getBooks().add(book);
        authorRepository.save(author);  // will save both book and author

        // create a response also
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());

        return bookResponseDto;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
