package com.example.hackathon.repository;

import com.example.hackathon.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
}
