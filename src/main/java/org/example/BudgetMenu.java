package org.example;

import java.time.LocalDate;

public class BudgetMenu {
    private final BudgetManager budget;
    private final TransactionFileHandler fileHandler;

    public BudgetMenu(BudgetManager budget, TransactionFileHandler fileHandler) {
        this.budget = budget;
        this.fileHandler = fileHandler;
    }

    public void start() {

        while (true) {

            IO.println("\n===== BUDGETHANTERAREN =====");
            IO.println("1. Lägg till transaktion");
            IO.println("2. Visa alla transaktioner");
            IO.println("3. Visa saldo och sammanställning");
            IO.println("4. Filtrera transaktioner");
            IO.println("5. Spara till fil");
            IO.println("6. Avsluta");

            String choice = IO.readln("Välj alternativ: ");


            switch (choice) {
                case "1" -> addTransaction();
                case "2" -> IO.println(budget.getTransactions());

                case "3" -> {
                    IO.println("Saldo: " + budget.getBalance());
                    IO.println(budget.getCategorySummary());
                }

                case "4" -> filterTransactions();
                case "5" -> IO.println("Sparning kommer snart.");

                case "6" -> {
                    IO.println("Programmet avslutas.");
                    return;
                }

                default -> IO.println("Ogiltigt alternativ.");
            }

        }

    }

    private void addTransaction() {

        try {
            LocalDate date = LocalDate.parse(IO.readln("Datum (ÅÅÅÅ-MM-DD: "));
            String category = IO.readln("Kategori: ");
            double amount = Double.parseDouble(IO.readln("Belopp: "));

            IO.println("1. Inkomst");
            IO.println("2. Utgift");

            String typeChoice = IO.readln("Välj typ: ");

            TransactionType type = switch (typeChoice) {
                case "1" -> TransactionType.INKOMST;
                case "2" -> TransactionType.UTGIFT;
                default -> throw new IllegalArgumentException("Ogiltig transaktionstyp.");

            };

            Transaction transaction = new Transaction(date, category, amount, type);

            budget.addTransaction(transaction);

            IO.println("Transaktionen har lagts till.");


        } catch (InvalidTransactionException | IllegalArgumentException e) {
            IO.println("Felaktig inmatning: " + e.getMessage());
        }
    }

    private void filterTransactions() {

        IO.println("1. Visa inkomster");
        IO.println("2. Visa utgifter");

        String choice = IO.readln("Välj alternativ: ");

        switch (choice) {
            case "1" -> IO.println(
                    budget.getTransactionsByType(TransactionType.INKOMST)
            );

            case "2" -> IO.println(
                    budget.getTransactionsByType(TransactionType.UTGIFT)
            );

            default -> IO.println("Ogiltigt alternativ.");
        }
    }
}
