package com.example.controller;

import com.example.entity.Transaction;
import com.example.repo.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionCrudController {
    private final TransactionRepository repo;

    @GetMapping
    public List<Transaction> all() { return repo.findAll(); }

    @PostMapping
    public Transaction create(@RequestBody Transaction tx) { return repo.save(tx); }
}
