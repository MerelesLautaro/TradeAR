package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.Exchange;
import com.lautadev.tradear.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDTO {
    private Long id;
    private LocalDateTime date;
    private List<ItemDTO> itemOffered;
    private List<ItemDTO> itemRequested;
    private UserSecDTO issuingUser;
    private UserSecDTO receivingUser;
    private Status status;
    private ChatDTO chat;

    public static ExchangeDTO fromExchange(Exchange exchange){
        if(exchange == null){
            return null;
        }

        List<ItemDTO> itemDTOSOffered = new ArrayList<>();
        if(exchange.getItemOffered() != null){
            itemDTOSOffered = exchange.getItemOffered().stream()
                    .map(ItemDTO::fromItem)
                    .collect(Collectors.toList());
        }

        List<ItemDTO> itemDTOSRequested = new ArrayList<>();
        if(exchange.getItemRequested() != null){
            itemDTOSRequested = exchange.getItemRequested().stream()
                    .map(ItemDTO::fromItem)
                    .collect(Collectors.toList());
        }


        return new ExchangeDTO(
                exchange.getId(),
                exchange.getDate(),
                itemDTOSOffered,
                itemDTOSRequested,
                UserSecDTO.fromUser(exchange.getIssuingUser()),
                UserSecDTO.fromUser(exchange.getReceivingUser()),
                exchange.getStatus(),
                ChatDTO.fromChat(exchange.getChat())
        );
    }
}
