package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
