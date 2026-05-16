package com.app.springapp.domain.vo;

import com.app.springapp.domain.dto.request.ChatRoomRequestDTO;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Data
public class ChatRoomVO {
    private Long id;
    private String chatRoomName;
    private String chatRoomType;
    private LocalDateTime chatRoomCreateAt;
    private String chatRoomProfile;
    private boolean chatRoomIsDeleted;
    private Long userId;
    private String chatRoomDetail;
    private int ChatRoomLimit;

    public static ChatRoomVO from(ChatRoomRequestDTO chatRoomRequestDTO) {
        ChatRoomVO chatRoomVO = new ChatRoomVO();
        chatRoomVO.setChatRoomName(chatRoomRequestDTO.getChatRoomName());
        chatRoomVO.setChatRoomType(chatRoomRequestDTO.getChatRoomType());

//        기본 프로필 개념
        String chatRoomProfile = chatRoomRequestDTO.getChatRoomProfile();
        String finalProfile = chatRoomProfile != null ? chatRoomProfile : "default.jpg";
        chatRoomVO.setChatRoomProfile(finalProfile);

        chatRoomVO.setChatRoomLimit(chatRoomRequestDTO.getChatRoomLimit());
        chatRoomVO.setChatRoomDetail(chatRoomRequestDTO.getChatRoomDetail());

        return chatRoomVO;
    }
}
