package com.backend.Library_Management_System.Repository;

import com.backend.Library_Management_System.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>
{

    @Query(value = "select * from transaction t where t.card_card_no=:cardId AND t.transaction_status='SUCCESS'",nativeQuery = true)
    List<Transaction> getAllSuccessfullTrnxsWithCardNo(int cardId);


    @Query(value = "select * from transaction t where t.Library_card_card_no=:cardId AND t.transaction_status='FAILED'", nativeQuery = true )
    List<Transaction> getAllFailedTnxsWithCardNo(int cardId);

}
