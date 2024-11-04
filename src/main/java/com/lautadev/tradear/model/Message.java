package com.lautadev.tradear.model;

import com.lautadev.tradear.dto.MessageDTO;
import com.lautadev.tradear.dto.SendMessageDTO;
import com.lautadev.tradear.repository.IChatRepository;
import com.lautadev.tradear.repository.IUserSecRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_sender")
    private UserSec sender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_chat")
    private Chat chat;

    public static Message fromDTO(SendMessageDTO sendMessageDTO, IUserSecRepository userSecRepository, IChatRepository chatRepository) {
        if (sendMessageDTO == null) {
            return null;
        }

        Message message = new Message();

        message.setId(sendMessageDTO.getId());
        message.setContent(sendMessageDTO.getContent());
        message.setTimestamp(sendMessageDTO.getTimestamp());

        Optional<UserSec> sender = userSecRepository.findById(sendMessageDTO.getSenderId());
        sender.ifPresent(message::setSender);

        Optional<Chat> chat = chatRepository.findById(sendMessageDTO.getChatId());
        chat.ifPresent(message::setChat);

        return message;
    }
}
