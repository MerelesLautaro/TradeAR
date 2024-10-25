package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.ItemDTO;
import com.lautadev.tradear.model.Item;
import com.lautadev.tradear.repository.IItemRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements IItemService{
    @Autowired
    private IItemRepository iItemRepository;

    @Override
    public ItemDTO saveItem(Item item) {
        if(item != null) {
            iItemRepository.save(item);
        }
        return ItemDTO.fromItem(item);
    }

    @Override
    public List<ItemDTO> getItems() {
        List<Item> items = iItemRepository.findAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for(Item item:items){
            itemDTOS.add(ItemDTO.fromItem(item));
        }

        return itemDTOS;
    }

    @Override
    public Optional<ItemDTO> findItem(Long id) {
        Item item = iItemRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return Optional.ofNullable(ItemDTO.fromItem(item));
    }

    @Override
    public void deleteItem(Long id) {
        iItemRepository.deleteById(id);
    }

    @Override
    public ItemDTO editItem(Long id, Item item) {
        Item itemEdit = iItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(item,itemEdit);

        return this.saveItem(itemEdit);
    }

    @Override
    public List<ItemDTO> findItemsNotBelongingToUser(Long id) {
        List<Item> items = iItemRepository.findItemsNotBelongingToUser(id);
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for(Item item: items){
            itemDTOS.add(ItemDTO.fromItem(item));
        }

        return itemDTOS;
    }
}
