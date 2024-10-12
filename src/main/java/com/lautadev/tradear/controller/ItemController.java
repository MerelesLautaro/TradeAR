package com.lautadev.tradear.controller;

import com.lautadev.tradear.model.Item;
import com.lautadev.tradear.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemService iItemService;

    @PostMapping("/save")
    public ResponseEntity<String> saveItem(@RequestBody Item item) {
        iItemService.saveItem(item);
        return ResponseEntity.ok("Item saved Successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Item>> getItems() {
        return ResponseEntity.ok(iItemService.getItems());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Item> findItem(@PathVariable Long id) {
        Optional<Item> item = iItemService.findItem(id);
        return item.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        iItemService.deleteItem(id);
        return ResponseEntity.ok("Item deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Item> editItem(@PathVariable Long id, @RequestBody Item item) {
        return ResponseEntity.ok(iItemService.editItem(id, item));
    }
}
