package org.example;

import java.util.List;

import java.util.Map;
import java.util.HashMap;

public class BudgetManager {

    // Skapar ett objekt som kan lagra Transaction-objekt
    private Repository<Transaction> repository = new Repository<>();

    // Lägger till transaktioner
    // Kontrollerar beloppet och sparar det
    public void addTransaction(Transaction transaction){

        if(transaction.amount() <= 0){
            throw new InvalidTransactionException("Beloppet måste vara större än 0");
        }
        repository.add(transaction);
    }

    // Hämtar transaktionerna
    public List<Transaction> getTransactions(){
        return repository.findAll();
    }

    public double getBalance() {
        double balance = 0.0;

        for (Transaction transaction : repository.findAll()) {

            // om det är en inkomst lägger vi till beloppet
            if (transaction.type() == TransactionType.INKOMST)
                balance += transaction.amount();
            else {
                balance -= transaction.amount();
            }
        }
        return balance;
    }

    public Map<String, Double> getCategorySummary() {

        // Skapar en Map som lagrar kategori o tillhörande summa
        Map<String, Double> summary = new HashMap<>();

        for (Transaction transaction : repository.findAll()) {

            // hämtar kategori och belopp
            String category = transaction.category();
            double amount = transaction.amount();

            // utgifter räknas som negativa belopp
            if (transaction.type() == TransactionType.UTGIFT){
                amount = -amount;
            }

            // Hämtar tidigare summa för kategorin, eller 0 om den saknas
            double current = summary.getOrDefault(category, 0.0);

            // Updaterar summan för kategorin
            summary.put(category, current + amount);
        }
        return summary;

    }

    public List<Transaction> getTransactionsByType(TransactionType type){
        //Hämtar bara transaktioner som matchar angiven typ
        return repository.findWhere(
                transaction -> transaction.type() == type
        );
    }

    public void loadTransactions(List<Transaction> transactions){

        for (Transaction transaction : transactions) {
            addTransaction(transaction);
        }
    }

}
