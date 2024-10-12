package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.Chat;
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
public class ChatDTO {
    private Long id;
    private String name;
    private List<MessageDTO> messages;

    public static ChatDTO fromChat(Chat chat){
        if(chat == null){
            return null;
        }

        List<MessageDTO> messageDTOS = new ArrayList<>();
        if(chat.getMessages() != null){
            messageDTOS = chat.getMessages().stream()
                    .map(MessageDTO::fromMessage)
                    .collect(Collectors.toList());
        }

        return new ChatDTO(
                chat.getId(),
                chat.getName(),
                messageDTOS
        );
    }
}
