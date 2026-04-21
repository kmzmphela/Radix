package com.radix.assessment.loan.service;

import com.radix.assessment.loan.model.Loan;
import com.radix.assessment.loan.model.Status;
import com.radix.assessment.loan.persistance.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoanService {

    @Autowired
    LoanRepository repository;

    public Loan creatLoan(Loan loan){
        loan.setStatus(Status.ACTIVE);
        log.debug("Saving loan to DB",loan);
        return repository.save(loan);
    }

    public Loan getLoanByID(Long id){
        log.debug("Retrieving loan from DB of id: ",id);
        return repository.getReferenceById(id);
    }
}
