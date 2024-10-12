package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    public Item saveItem(Item item);
    public List<Item> getItems();
    public Optional<Item> findItem(Long id);
    public void deleteItem(Long id);
    public Item editItem(Long id, Item item);
}
