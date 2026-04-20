package com.radix.assessment.loan.service;

import com.radix.assessment.loan.model.Loan;
import com.radix.assessment.loan.model.Status;
import com.radix.assessment.loan.persistance.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    LoanRepository repository;

    public Loan creatLoan(Loan loan){
        loan.setStatus(Status.ACTIVE);
        return repository.save(loan);
    }

    public Loan getLoanByID(Long id){
        return repository.getReferenceById(id);
    }
}
