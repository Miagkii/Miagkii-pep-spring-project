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

    private boolean messageValidation(String messageText){
        if (messageText.length() > 255 || messageText.isBlank() || messageText == null) {
            return false;
        } else return true;
    }

    public Message createMessage(Message message) {

        if (accountRepository.findByAccountId(message.getPostedBy()).isPresent() && messageValidation(message.getMessageText())) {
            Message createdMessage = new Message(message.getPostedBy(), message.getMessageText(), message.getTimePostedEpoch());
            return messageRepository.save(createdMessage);
        }
        return null;

    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getByMessageId(Integer id) {
        return messageRepository.findByMessageId(id).orElse(null);
    }

    public Integer deleteMessegeById(Integer id) {
        if (messageRepository.findByMessageId(id).isPresent()) {
            messageRepository.deleteById(id);
            return 1;
        }
        return null;
    }

    public Integer updateMessage(Integer id, String messageText) {

        if (messageRepository.findByMessageId(id).isPresent() && messageValidation(messageText)) {
            Message message = messageRepository.findByMessageId(id).get();
            message.setMessageText(messageText);
            messageRepository.save(message);
            return 1;
        } else {
            return 0;
        }
    }

}
