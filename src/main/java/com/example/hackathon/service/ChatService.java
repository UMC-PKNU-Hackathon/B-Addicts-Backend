package com.example.hackathon.service;

import com.example.hackathon.dto.request.CreateChatRoomDto;
import com.example.hackathon.entity.ChatRoom;
import com.example.hackathon.entity.ChatUser;
import com.example.hackathon.entity.User;
import com.example.hackathon.repository.ChatRoomRepository;
import com.example.hackathon.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatUserRepository chatUserRepository;

    public void create(User user, CreateChatRoomDto createChatRoomDto) {
        ChatRoom chatRoom = ChatRoom.builder()
                .user(user)
                .chatRoomName(createChatRoomDto.getChatRoomName())
                .deadline(createChatRoomDto.getDeadline())
                .targetPeriod(createChatRoomDto.getTargetPeriod())
                .keyword(createChatRoomDto.getKeyword())
                .tag(createChatRoomDto.getTag())
                .chatMembers(createChatRoomDto.getChatMembers())
                .build();

        chatRoomRepository.save(chatRoom);

        ChatUser chatUser = ChatUser.builder()
                .chatRoom(chatRoom)
                .user(user)
                .build();

        chatUserRepository.save(chatUser);
    }
}
