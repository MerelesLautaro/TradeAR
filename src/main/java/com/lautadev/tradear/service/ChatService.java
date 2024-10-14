package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.ChatDTO;
import com.lautadev.tradear.model.Chat;
import com.lautadev.tradear.repository.IChatRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService implements IChatService{

    @Autowired
    private IChatRepository chatRepository;

    @Override
    @Transactional
    public ChatDTO saveChat(Chat chat) {
        if(chat != null){
            chatRepository.save(chat);
        }

        return ChatDTO.fromChat(chat);
    }

    @Override
    public List<ChatDTO> getChats() {
        List<Chat> chats  = chatRepository.findAll();
        List<ChatDTO> chatDTOS = new ArrayList<>();

        for(Chat chat:chats){
            chatDTOS.add(ChatDTO.fromChat(chat));
        }

        return chatDTOS;
    }

    @Override
    public Optional<ChatDTO> findChat(Long id) {
        Chat chat = chatRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entity Not Found"));
        return Optional.ofNullable(ChatDTO.fromChat(chat));
    }

    @Override
    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }

    @Override
    public ChatDTO editChat(Long id, Chat chat) {
        Chat chatEdit = chatRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(chat,chatEdit);

        return this.saveChat(chatEdit);
    }
}
