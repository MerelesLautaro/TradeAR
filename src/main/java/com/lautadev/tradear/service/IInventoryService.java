package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.InventoryDTO;
import com.lautadev.tradear.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface IInventoryService {
    public InventoryDTO saveInventory(Inventory inventory);
    public List<InventoryDTO> getInventories();
    public Optional<InventoryDTO> findInventory(Long id);
    public void deleteInventory(Long id);
    public InventoryDTO editInventory(Long id, Inventory inventory);
}
