package com.radix.assessment.loan.controller;

import com.radix.assessment.loan.model.Loan;
import com.radix.assessment.loan.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/loans")
    public Loan createNewLoan(@RequestBody Loan loan){
        log.debug("creating new loan: ",loan);
        return loanService.creatLoan(loan);
    }


    @GetMapping("/loans/{loanID}")
    public Loan GetLoan(@PathVariable Long loanID){
        log.debug("retrieving loan for id: ",loanID);
        return loanService.getLoanByID(loanID);
    }
}




