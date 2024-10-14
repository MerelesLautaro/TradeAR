package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.Inventory;
import com.lautadev.tradear.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {
    private Long id;
    private List<ItemDTO> items;

    public static InventoryDTO fromInventory(Inventory inventory){
        if(inventory == null){
            return null;
        }

        List<ItemDTO> itemDTOS = new ArrayList<>();
        if(inventory.getItems() != null){
            itemDTOS = inventory.getItems().stream()
                    .map(ItemDTO::fromItem)
                    .collect(Collectors.toList());
        }

        return new InventoryDTO(
                inventory.getId(),
                itemDTOS
        );
    }
}
