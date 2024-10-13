package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.ItemDTO;
import com.lautadev.tradear.model.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    public ItemDTO saveItem(Item item);
    public List<ItemDTO> getItems();
    public Optional<ItemDTO> findItem(Long id);
    public void deleteItem(Long id);
    public ItemDTO editItem(Long id, Item item);
}
