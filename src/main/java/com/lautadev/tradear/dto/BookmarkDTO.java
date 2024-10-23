package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.Bookmark;
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
public class BookmarkDTO {
    private Long id;
    private List<ItemDTO> itemDTOS;
    private UserSecDTO userSecDTO;

    public static BookmarkDTO fromBookmark(Bookmark bookmark){
        if(bookmark == null){
            return null;
        }

        List<ItemDTO> itemDTOS = new ArrayList<>();
        if(bookmark.getItems() != null){
            itemDTOS = bookmark.getItems().stream()
                    .map(ItemDTO::fromItem)
                    .collect(Collectors.toList());
        }

        return new BookmarkDTO(
                bookmark.getId(),
                itemDTOS,
                UserSecDTO.fromUser(bookmark.getUserSec())
        );
    }
}
