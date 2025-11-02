package com.example.repo;


import com.example.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t " +
            "where t.accountId = :accountId and t.txnDate between :from and :to " +
            "order by t.txnDate asc, t.id asc")
    List<Transaction> findForPeriod(String accountId, LocalDate from, LocalDate to);
}
