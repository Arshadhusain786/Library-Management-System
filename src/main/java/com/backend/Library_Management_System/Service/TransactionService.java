package com.backend.Library_Management_System.Service;

import com.backend.Library_Management_System.DTO.IssueBookRequestDto;
import com.backend.Library_Management_System.DTO.IssueBookResponseDto;
import com.backend.Library_Management_System.Entity.Book;
import com.backend.Library_Management_System.Entity.LibraryCard;
import com.backend.Library_Management_System.Entity.Transaction;
import com.backend.Library_Management_System.Enum.CardStatus;
import com.backend.Library_Management_System.Enum.TransactionStatus;
import com.backend.Library_Management_System.Repository.BookRepository;
import com.backend.Library_Management_System.Repository.CardRepository;
import com.backend.Library_Management_System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService
{
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private JavaMailSender emailSender;

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception
    {
        // Create transaction object
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssuedOperation(true);


        LibraryCard card;
        try {
             card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }

        catch (Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid card id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid card id");
        }

        Book book;

        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch(Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid book id");
            transactionRepository.save(transaction);
            throw new Exception("Invalid book id");

        }

        // Both card and Book are valid
        transaction.setBook(book);
        transaction.setCard(card);

        if(card.getStatus()!= CardStatus.ACTIVATED)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);

            transaction.setMessage("Your card is not activated");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not activated");
        }
        if(book.isIssued()==true)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);

            transaction.setMessage("Sorry! book is already issued");
            transactionRepository.save(transaction);

            throw new Exception("Sorry! book is already issued");
        }

        // Issue the Book
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        transaction.setMessage("Transaction was succesfull");
        book.setIssued(true);
        book.setCard(card);
        book.getTransaction().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBooksIssued().add(book);


        cardRepository.save(card); // will save book and transaction also
        //bcz it is parent of both

       //prepare a response DTO
        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setTransactionId(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);
        issueBookResponseDto.setBookName(book.getTitle());

        //Send an email

        String text= "Hii "+card.getStudent().getName()+" your request was accepted,and :"+ book.getTitle()+" book." +
                " has been issued successfully and  "+ transaction.getTransactionNumber()+" Thank you for using our services!";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("arzubackend443@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("issue book notification");
        message.setText(text);
        emailSender.send(message);


        return issueBookResponseDto;


    }
    public String getAllTnxs(int cardNo){
        List<Transaction> transactionList= transactionRepository.getAllSucessfullTnxsWithCardNo(cardNo);
        String ans= "";

        for(Transaction transaction: transactionList )
        {
            ans+= transaction.getTransactionNumber();
            ans+="\n";
        }
        return ans;
    }
    public String getAllFTnxs(int cardNo){
        List<Transaction> transactionList= transactionRepository.getAllFailedTnxsWithCardNo(cardNo);
        String ans= "";

        for(Transaction transaction: transactionList )
        {
            ans+= transaction.getTransactionNumber();
            ans+="\n";
        }
        return ans;
    }

}
