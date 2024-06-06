package com.backend.Library_Management_System.Entity;

import com.backend.Library_Management_System.Enum.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String transactionNumber;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date transactionDate;

    private String transactionMessage;

    private boolean isIssuedOperation;

    private String message;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Book book;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    LibraryCard card;

}