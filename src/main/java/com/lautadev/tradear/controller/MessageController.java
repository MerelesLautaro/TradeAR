package com.lautadev.tradear.controller;

import com.lautadev.tradear.dto.MessageDTO;
import com.lautadev.tradear.dto.SendMessageDTO;
import com.lautadev.tradear.model.Message;
import com.lautadev.tradear.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@PreAuthorize("permitAll()")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @PostMapping("/save")
    public ResponseEntity<MessageDTO> saveMessage(@RequestBody SendMessageDTO sendMessageDTO){
        return ResponseEntity.ok(messageService.saveMessage(sendMessageDTO));
    }

    @GetMapping("/get")
    public ResponseEntity<List<MessageDTO>> getMessages(){
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MessageDTO> findMessage(@PathVariable Long id){
        Optional<MessageDTO> message = messageService.findMessage(id);
        return message.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id){
        messageService.deleteMessage(id);
        return ResponseEntity.ok("Message deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<MessageDTO> editMessage(@PathVariable Long id, @RequestBody Message message){
        return ResponseEntity.ok(messageService.editMessage(id,message));
    }
}
