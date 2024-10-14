package com.lautadev.tradear.controller;

import com.lautadev.tradear.dto.InventoryDTO;
import com.lautadev.tradear.model.Inventory;
import com.lautadev.tradear.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private IInventoryService iInventoryService;

    @PostMapping("/save")
    public ResponseEntity<InventoryDTO> saveInventory(@RequestBody Inventory inventory){
        return ResponseEntity.ok(iInventoryService.saveInventory(inventory));
    }

    @GetMapping("/get")
    public ResponseEntity<List<InventoryDTO>> getInventories(){
        return ResponseEntity.ok(iInventoryService.getInventories());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InventoryDTO> findInventory(@PathVariable Long id){
        Optional<InventoryDTO> inventoryDTO = iInventoryService.findInventory(id);
        return inventoryDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id){
        iInventoryService.deleteInventory(id);
        return ResponseEntity.ok("Inventory deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<InventoryDTO> editInventory(@PathVariable Long id, @RequestBody Inventory inventory){
        return ResponseEntity.ok(iInventoryService.editInventory(id,inventory));
    }
}
