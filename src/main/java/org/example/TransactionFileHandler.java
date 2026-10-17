package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TransactionFileHandler {

    public void save(List<Transaction> transactions) throws IOException {

        //Bestämmer vilken fil vi ska spara till
        Path path = Path.of("transactions.csv");

        // Bygger upp texten som ska sparas i filen
        StringBuilder content = new StringBuilder();

        // Går igenom alla transaktioner
        for (Transaction transaction : transactions) {

            // Skapar en rad med transaktionens information
            content.append(transaction.date()).append(";")
                    .append(transaction.category()).append(";")
                    .append(transaction.amount()).append(";")
                    .append(transaction.type()).append("\n");
        }

        // Skriv innehållet till filen
        Files.writeString(path, content.toString());
    }
}
