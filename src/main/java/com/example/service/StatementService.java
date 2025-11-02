package com.example.service;

import com.example.entity.Transaction;
import com.example.repo.TransactionRepository;
import com.example.util.PdfUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatementService {
    private final TransactionRepository repo;

    public byte[] generate(String accountId, int year, int month) {
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = from.withDayOfMonth(from.lengthOfMonth());
        List<Transaction> txns = repo.findForPeriod(accountId, from, to);
        return PdfUtil.statementPdf(accountId, year, month, txns);
    }
}
