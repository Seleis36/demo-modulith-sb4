package com.example.inventory.internal;

import com.example.catalog.CoffeeCreated;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryListener {

    private final StockRepository repository;

    InventoryListener(StockRepository repository) {
        this.repository = repository;
    }

    @ApplicationModuleListener
    void on(CoffeeCreated event){
        repository.save(new StockEntity(event.coffeeId(), 0));
    }
}
