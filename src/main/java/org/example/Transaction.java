package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record Transaction(
        LocalDate date,
        String category,
        double amount,
        TransactionType type
) {
}
