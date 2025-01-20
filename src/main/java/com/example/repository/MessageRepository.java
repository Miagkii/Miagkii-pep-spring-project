package com.example.repository;

import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    Optional<Message> findByPostedBy(Integer username);
    List<Message> findAll();
}
