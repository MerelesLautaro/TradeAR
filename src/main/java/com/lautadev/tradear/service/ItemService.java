package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Item;
import com.lautadev.tradear.repository.IItemRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements IItemService{
    @Autowired
    private IItemRepository iItemRepository;

    @Override
    public Item saveItem(Item item) {
        if(item != null) {
            return iItemRepository.save(item);
        }

        return null;
    }

    @Override
    public List<Item> getItems() {
        return iItemRepository.findAll();
    }

    @Override
    public Optional<Item> findItem(Long id) {
        return iItemRepository.findById(id);
    }

    @Override
    public void deleteItem(Long id) {
        iItemRepository.deleteById(id);
    }

    @Override
    public Item editItem(Long id, Item item) {
        Item itemEdit = iItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(item,itemEdit);

        return this.saveItem(itemEdit);
    }
}
