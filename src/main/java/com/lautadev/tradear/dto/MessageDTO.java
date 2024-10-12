package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.Message;
import com.lautadev.tradear.model.UserSec;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private UserSecDTO sender;

    public static MessageDTO fromMessage(Message message){
        if(message == null){
            return null;
        }

        return new MessageDTO(
                message.getId(),
                message.getContent(),
                message.getTimestamp(),
                UserSecDTO.fromUser(message.getSender())
        );
    }
}
