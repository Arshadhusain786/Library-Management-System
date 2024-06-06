package com.backend.Library_Management_System.Service;

import com.backend.Library_Management_System.Entity.Author;
import com.backend.Library_Management_System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService
{
 @Autowired
    AuthorRepository authorRepository;

  public void addAuthor(Author author)
  {
       authorRepository.save(author);
  }
  public List<Author> getAuthors()
  {
      return authorRepository.findAll();

  }

    public String getMobileNumberById(int id) throws Exception {
        Author author;
        try {
            author = authorRepository.findById(id).get();
            return author.getMobNo();
        }
        catch (Exception e)
        {
            throw new Exception("Author Does not Exist");
        }
    }


}
