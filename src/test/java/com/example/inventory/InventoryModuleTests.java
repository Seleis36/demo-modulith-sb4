package com.example.inventory;

import com.example.catalog.CoffeeCreated;
import com.example.inventory.internal.StockEntity;
import com.example.inventory.internal.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class InventoryModuleTests {

    @Autowired
    StockRepository stockRepository;

    @Test
    void createsStockOnCoffeeCreated(Scenario scenario) {
        scenario.publish(new CoffeeCreated(1L, "Espresso"))
                .andWaitForStateChange(() -> stockRepository.findAll().isEmpty() ? null : stockRepository.findAll())
                .andVerify((List<StockEntity> stocks) -> assertThat(stocks).hasSize(1));
    }
}
