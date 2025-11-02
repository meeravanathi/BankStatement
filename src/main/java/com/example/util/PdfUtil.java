package com.example.util;

import com.example.entity.Transaction;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PdfUtil {
    public static byte[] statementPdf(String accountId, int year, int month, List<Transaction> txns) {
        try {
            Document doc = new Document(PageSize.A4, 36, 36, 36, 36);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(doc, out);
            doc.open();

            Font h1 = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font h2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font normal = new Font(Font.HELVETICA, 10);

            doc.add(new Paragraph("Bank Statement", h1));
            doc.add(new Paragraph(String.format("Account: %s | Period: %d-%02d", accountId, year, month), h2));
            doc.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{20, 50, 15, 15});
            addHeader(table, "Date"); addHeader(table, "Description");
            addHeader(table, "Type"); addHeader(table, "Amount");

            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            BigDecimal balance = BigDecimal.ZERO;

            for (Transaction t : txns) {
                table.addCell(new Phrase(df.format(t.getTxnDate()), normal));
                table.addCell(new Phrase(t.getDescription(), normal));
                table.addCell(new Phrase(t.getType(), normal));
                table.addCell(new Phrase(t.getAmount().toPlainString(), normal));
                balance = balance.add(t.getAmount());
            }

            doc.add(table);
            doc.add(Chunk.NEWLINE);
            doc.add(new Paragraph("Closing Balance: " + balance.toPlainString(), h2));
            doc.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }

    private static void addHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.HELVETICA, 10, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}
