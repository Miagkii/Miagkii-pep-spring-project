package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.*;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) {
        if (message.getMessageText().length() > 255 || message.getMessageText().isBlank()) {
            return null;
        }
        if (messageRepository.findByPostedBy(message.getPostedBy()).isPresent()) {
            Message createdMessage = new Message(message.getPostedBy(), message.getMessageText(), message.getTimePostedEpoch());
            return messageRepository.save(createdMessage);
        }
        return null;

    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }



}
