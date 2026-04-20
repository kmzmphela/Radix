package com.radix.assessment.loan.service;

import com.radix.assessment.TestBase;
import com.radix.assessment.loan.model.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;


public class TestLoanService extends TestBase {

    @Autowired
    private LoanService loanService;

    @Test
    public void testCreateLoan(){
        long loanId = createLoan(12,50000);
        assertNotNull(loanService.getLoanByID(loanId));
    }
}
