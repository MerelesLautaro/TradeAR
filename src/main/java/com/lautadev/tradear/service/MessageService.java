package com.lautadev.tradear.service;

import com.lautadev.tradear.dto.MessageDTO;
import com.lautadev.tradear.dto.SendMessageDTO;
import com.lautadev.tradear.model.Message;
import com.lautadev.tradear.repository.IChatRepository;
import com.lautadev.tradear.repository.IMessageRepository;
import com.lautadev.tradear.repository.IUserSecRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {

    private final IMessageRepository messageRepository;
    private final IUserSecRepository userSecRepository;
    private final IChatRepository chatRepository;

    public MessageService(IMessageRepository messageRepository, IUserSecRepository userSecRepository, IChatRepository chatRepository){
        this.messageRepository = messageRepository;
        this.userSecRepository = userSecRepository;
        this.chatRepository =  chatRepository;
    }

    @Override
    public MessageDTO saveMessage(SendMessageDTO sendMessageDTO) {
        Message message = Message.fromDTO(sendMessageDTO,userSecRepository,chatRepository);
        return MessageDTO.fromMessage(messageRepository.save(message));
    }

    @Override
    public List<MessageDTO> getMessages() {
        List<Message> messages = messageRepository.findAll();
        List<MessageDTO> messageDTOS = new ArrayList<>();

        for(Message message: messages){
            messageDTOS.add(MessageDTO.fromMessage(message));
        }

        return messageDTOS;
    }

    @Override
    public Optional<MessageDTO> findMessage(Long id) {
        Message message = messageRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(("Entity Not Found")));
        return Optional.ofNullable(MessageDTO.fromMessage(message));
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public MessageDTO editMessage(Long id, Message message) {
        Message messageEdit = messageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(message,messageEdit);

        return MessageDTO.fromMessage(messageRepository.save(message));
    }
}
