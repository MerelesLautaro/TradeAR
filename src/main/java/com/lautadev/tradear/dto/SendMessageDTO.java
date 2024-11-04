package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageDTO {
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private Long senderId;
    private Long chatId;

    public static SendMessageDTO fromSendMessage(Message message){
        if(message == null){
            return null;
        }

        return new SendMessageDTO(
                message.getId(),
                message.getContent(),
                message.getTimestamp(),
                message.getSender().getId(),
                message.getChat().getId()
        );
    }
}