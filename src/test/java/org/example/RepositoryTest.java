package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

class RepositoryTest {

    @Test
    void addShouldStoreItem() {

        // Arrange – förbered testet
        Repository<String> repository = new Repository<>();

        // Act – utför det vi vill testa
        repository.add("Mat");

        // Assert – kontrollera resultatet
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void findWhereShouldFilterItems() {

        // Arrange
        Repository<Integer> repository = new Repository<>();
        repository.add(10);
        repository.add(20);
        repository.add(30);

        // Act
        List<Integer> result = repository.findWhere(number -> number > 15);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void findAllShouldReturnAllItems() {

        // Arrange
        Repository<String> repository = new Repository<>();
        repository.add("Mat");
        repository.add("Hyra");
        repository.add("El");

        // Act
        List<String> result = repository.findAll();

        // Assert
        assertEquals(3, result.size());
        assertEquals("Mat", result.get(0));
        assertEquals("Hyra", result.get(1));
        assertEquals("El", result.get(2));
    }

}