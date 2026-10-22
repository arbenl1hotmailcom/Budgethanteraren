package org.example;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BudgetManagerTest {

    @Test
    void getBalanceShouldCalculateCorrectly() {

        // Arrange
        BudgetManager budget = new BudgetManager();

        budget.addTransaction(
                new Transaction(LocalDate.now(), "Lön", 25000, TransactionType.INKOMST)
        );

        budget.addTransaction(
                new Transaction(LocalDate.now(), "Mat", 5000, TransactionType.UTGIFT)
        );

        // Act
        double balance = budget.getBalance();

        // Assert
        assertEquals(20000, balance);
    }

    @Test
    void getCategorySummaryShouldCalculateCorrectly() {

        // Arrange
        BudgetManager budget = new BudgetManager();

        budget.addTransaction(
                new Transaction(LocalDate.now(), "Mat", 500, TransactionType.UTGIFT)
        );

        budget.addTransaction(
                new Transaction(LocalDate.now(), "Mat", 200, TransactionType.UTGIFT)
        );

        // Act
        double total = budget.getCategorySummary().get("Mat");

        // Assert
        assertEquals(-700, total);
    }



    @Test
    void getBalanceShouldReturnZeroWhenEmpty() {

        // Arrange
        BudgetManager budget = new BudgetManager();

        // Act
        double balance = budget.getBalance();

        // Assert
        assertEquals(0, balance);
    }

    @Test
    void addTransactionShouldRejectInvalidAmount() {

        // Arrange
        BudgetManager budget = new BudgetManager();

        Transaction transaction = new Transaction(
                LocalDate.now(), "Mat", 0, TransactionType.UTGIFT
        );

        // Act & Assert
        assertThrows(InvalidTransactionException.class,
                () -> budget.addTransaction(transaction));
    }


    @Test
    void addTransactionShouldRejectNaN() {

        // Arrange
        BudgetManager budget = new BudgetManager();

        Transaction transaction = new Transaction(
                LocalDate.now(), "Mat", Double.NaN, TransactionType.UTGIFT
        );

        // Act & Assert
        assertThrows(InvalidTransactionException.class,
                () -> budget.addTransaction(transaction));
    }

}