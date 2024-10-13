package com.lautadev.tradear.controller;

import com.lautadev.tradear.dto.ItemDTO;
import com.lautadev.tradear.model.Item;
import com.lautadev.tradear.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
@PreAuthorize("permitAll()")
public class ItemController {

    @Autowired
    private IItemService iItemService;

    @PostMapping("/save")
    public ResponseEntity<ItemDTO> saveItem(@RequestBody Item item) {
        return ResponseEntity.ok(iItemService.saveItem(item));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ItemDTO>> getItems() {
        return ResponseEntity.ok(iItemService.getItems());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ItemDTO> findItem(@PathVariable Long id) {
        Optional<ItemDTO> item = iItemService.findItem(id);
        return item.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        iItemService.deleteItem(id);
        return ResponseEntity.ok("Item deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<ItemDTO> editItem(@PathVariable Long id, @RequestBody Item item) {
        return ResponseEntity.ok(iItemService.editItem(id, item));
    }
}
