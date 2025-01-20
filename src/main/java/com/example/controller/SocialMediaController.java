package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.*;
import com.example.service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        if (createdAccount == null) {
            if (accountService.isDuplicateAccount(account)) {
                return ResponseEntity.status(409).body(null);
            }
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.status(200).body(createdAccount);

    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        if (createdMessage == null) {
            return ResponseEntity.status(400).body(null);
        } else {
            return ResponseEntity.status(200).body(createdMessage);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account presentAccount = accountService.login(account);
        if (presentAccount == null) {
            return ResponseEntity.status(401).body(null);
        } else {
            return ResponseEntity.status(200).body(presentAccount);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> allMessages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(allMessages);
    }

}
