package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface IInventoryService {
    public void saveInventory(Inventory inventory);
    public List<Inventory> getInventories();
    public Optional<Inventory> findInventory(Long id);
    public void deleteInventory(Long id);
    public void editInventory(Long id, Inventory inventory);
}
