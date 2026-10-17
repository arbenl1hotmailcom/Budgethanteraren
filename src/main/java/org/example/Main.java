package org.example;

import java.time.LocalDate;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        BudgetManager budget = new BudgetManager();

        Transaction transaction = new Transaction(
                LocalDate.now(),
                "Mat",
                250,
                TransactionType.UTGIFT
        );

        budget.addTransaction(transaction);
        IO.println(budget.getTransactions());
    }
}
