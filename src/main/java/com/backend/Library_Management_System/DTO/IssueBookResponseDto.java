package com.backend.Library_Management_System.DTO;


import com.backend.Library_Management_System.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class IssueBookResponseDto
{
    private String transactionId;

    private String bookName;

    private TransactionStatus transactionStatus;

}
