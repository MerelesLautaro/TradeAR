package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.Exchange;
import com.lautadev.tradear.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDTO {
    private Long id;
    private LocalDate date;
    private List<ItemDTO> itemOffered;
    private List<ItemDTO> itemRequested;
    private UserSecDTO issuingUser;
    private UserSecDTO receivingUser;
    private Status status;
    private ChatDTO chat;

    public static ExchangeDTO fromExchange(Exchange exchange, List<ItemDTO> offeredItems, List<ItemDTO> requestedItems){
        if(exchange == null){
            return null;
        }

        return new ExchangeDTO(
                exchange.getId(),
                exchange.getDate(),
                offeredItems,
                requestedItems,
                UserSecDTO.fromUser(exchange.getIssuingUser()),
                UserSecDTO.fromUser(exchange.getReceivingUser()),
                exchange.getStatus(),
                ChatDTO.fromChat(exchange.getChat())
        );
    }
}
