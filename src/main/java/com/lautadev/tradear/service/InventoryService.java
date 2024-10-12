package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Inventory;
import com.lautadev.tradear.repository.IInventoryRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService implements IInventoryService{
    @Autowired
    private IInventoryRepository inventoryRepository;

    @Override
    public void saveInventory(Inventory inventory) {
        if(inventory != null) inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> findInventory(Long id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public void editInventory(Long id, Inventory inventory) {
        Inventory inventoryEdit = inventoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(inventory,inventoryEdit);

        this.saveInventory(inventoryEdit);
    }
}
