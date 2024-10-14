package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.InventoryDTO;
import com.lautadev.tradear.model.Inventory;
import com.lautadev.tradear.repository.IInventoryRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService implements IInventoryService{
    @Autowired
    private IInventoryRepository inventoryRepository;

    @Override
    public InventoryDTO saveInventory(Inventory inventory) {

        if(inventory != null) {
            inventoryRepository.save(inventory);
        }

        return InventoryDTO.fromInventory(inventory);
    }

    @Override
    public List<InventoryDTO> getInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        List<InventoryDTO> inventoryDTOS = new ArrayList<>();

        for(Inventory inventory: inventories){
            inventoryDTOS.add(InventoryDTO.fromInventory(inventory));
        }

        return inventoryDTOS;
    }

    @Override
    public Optional<InventoryDTO> findInventory(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Inventory Not Found"));
        return Optional.ofNullable(InventoryDTO.fromInventory(inventory));
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public InventoryDTO editInventory(Long id, Inventory inventory) {
        Inventory inventoryEdit = inventoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(inventory,inventoryEdit);

        return this.saveInventory(inventoryEdit);
    }
}
