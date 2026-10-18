package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

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

    public List<Transaction> load() throws IOException {

        // Anger vilken fil vi ska läsa
        Path path = Path.of("transactions.csv");

        //Skapar en lista som ska innehålla transaktionerna
        List<Transaction> transactions = new ArrayList<>();

        // Om filen saknas returnerar vi en tom lista
        if (!Files.exists(path)) {
            return transactions;
        }

        // Läser filen rad för rad
        for (String line : Files.readAllLines(path)) {

            // Delar upp raden vid varje semikolon
            String[] parts = line.split(";", -1);

            // Kontrollerar att raden innehåller 4 delar
            if (parts.length != 4){
                throw new IOException("Felaktig rad i CSV-filen: " + line);
            }
            try {
                // Omvandlar texten till rätt datatyper
                LocalDate date = LocalDate.parse(parts[0]);
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);
                TransactionType type = TransactionType.valueOf(parts[3]);

                // Skapar en transaktion och lägger till den i listan
                transactions.add(new Transaction(date, category, amount, type));
            } catch (IllegalArgumentException e) {
                throw new IOException("Ogiltiga värden i CSV-filen: " + line, e);
            }
        }
        return transactions;
    }
}
