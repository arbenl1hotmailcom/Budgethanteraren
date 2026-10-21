package org.example;

import java.io.IOException;
import java.time.LocalDate;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        BudgetManager budget = new BudgetManager();
        TransactionFileHandler fileHandler = new TransactionFileHandler();

        // Läs in sparade transaktioner vid programstart
        try {
            budget.loadTransactions(fileHandler.load());
            IO.println("Transaktionerna har lästs in!");
        } catch (IOException | InvalidTransactionException e) {
            IO.println("Fel vid inläsning: " + e.getMessage());
        }


        // Starta meny
        BudgetMenu menu = new BudgetMenu(budget, fileHandler);
        menu.start();


    }
}
