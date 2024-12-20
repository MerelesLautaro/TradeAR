package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.Category;
import com.lautadev.tradear.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private Date date;
    private String link;
    private int amount;
    private String category;
    private UserSecDTO userSecDTO;

    public static ItemDTO fromItem(Item item){
        if(item == null){
            return null;
        }

        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getDate(),
                item.getLink(),
                item.getAmount(),
                item.getCategory().getName(),
                UserSecDTO.fromUser(item.getInventory().getUserSec())
        );
    }
}
