package com.radix.assessment.loan.persistance;

import com.radix.assessment.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository  extends JpaRepository<Loan, Long> {
}
