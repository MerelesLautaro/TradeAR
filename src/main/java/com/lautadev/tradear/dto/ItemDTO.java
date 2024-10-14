package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.Category;
import com.lautadev.tradear.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private String link;
    private int amount;
    private String category;

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
                item.getCategory().getName()
        );
    }
}
