package com.backend.Library_Management_System.Repository;

import com.backend.Library_Management_System.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer>
{

}
