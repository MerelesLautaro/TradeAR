package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.MessageDTO;
import com.lautadev.tradear.dto.SendMessageDTO;
import com.lautadev.tradear.model.Message;

import java.util.List;
import java.util.Optional;

public interface IMessageService {
    public MessageDTO saveMessage(SendMessageDTO sendMessageDTO);
    public List<MessageDTO> getMessages();
    public Optional<MessageDTO> findMessage(Long id);
    public void deleteMessage(Long id);
    public MessageDTO editMessage(Long id,Message message);
}
