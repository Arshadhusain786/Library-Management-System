package com.backend.Library_Management_System.Controller;

import com.backend.Library_Management_System.DTO.IssueBookRequestDto;
import com.backend.Library_Management_System.DTO.IssueBookResponseDto;

import com.backend.Library_Management_System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/transaction")
public class TransactionController
{
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto)
    {
        IssueBookResponseDto issueBookResponseDto;

        try{
            issueBookResponseDto= transactionService.issueBook(issueBookRequestDto);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);

        }
        return new ResponseEntity(issueBookResponseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get")
    public String getAllTxns(@RequestParam("cardId") int cardId)
    {
        return  transactionService.getAllTnxs(cardId);
    }
    @GetMapping("/get_failed_tnxs")
    public String getAllFTnxs(@RequestParam("cardNo") int cardNo){

        return transactionService.getAllFTnxs(cardNo);

    }


}
