package com.radix.assessment.loan.controller;

import com.radix.assessment.loan.model.Loan;
import com.radix.assessment.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/loans")
    public Loan createNewLoan(@RequestBody Loan loan){
        return loanService.creatLoan(loan);
    }


    @GetMapping("/loans/{loanID}")
    public Loan GetLoan(@PathVariable Long loanID){
        return loanService.getLoanByID(loanID);
    }
}




