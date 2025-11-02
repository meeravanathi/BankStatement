package com.example.controller;

import com.example.service.EmailService;
import com.example.service.StatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statements")
@RequiredArgsConstructor
public class StatementController {
    private final StatementService statements;
    private final EmailService email;

    // Download PDF
    @GetMapping("/{accountId}")
    public ResponseEntity<byte[]> download(@PathVariable String accountId,
                                           @RequestParam int year,
                                           @RequestParam int month) {
        byte[] pdf = statements.generate(accountId, year, month);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=%s-%d-%02d.pdf".formatted(accountId, year, month))
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // Email PDF
    @PostMapping("/{accountId}/email")
    public ResponseEntity<String> email(@PathVariable String accountId,
                                        @RequestParam int year,
                                        @RequestParam int month,
                                        @RequestParam String to) {
        byte[] pdf = statements.generate(accountId, year, month);
        email.sendWithAttachment(to,
                "Your Bank Statement %d-%02d".formatted(year, month),
                "Please find attached your statement.",
                pdf,
                "%s-%d-%02d.pdf".formatted(accountId, year, month));
        return ResponseEntity.ok("Sent");
    }
}
