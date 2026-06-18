package com.app.springapp.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import com.app.springapp.domain.dto.ChatUserDTO;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Data
@Schema(description = "채팅방 참여 현황 응답 DTO")
public class ChatUserResponseDTO {
    @Schema(description = "채팅 멤버 번호", example = "1")
    private Long id;
    private Long userId;
    private Long chatRoomId;
    private String userNickname;
    private String userProfile;
    private String userEmail;
    private Long userExp;
    private Integer userLevel;
    private String userLevelName;

    public static ChatUserResponseDTO from(ChatUserDTO dto) {
        ChatUserResponseDTO res = new ChatUserResponseDTO();
        res.setId(dto.getId());
        res.setUserId(dto.getUserId());
        res.setChatRoomId(dto.getChatRoomId());
        res.setUserNickname(dto.getUserNickname());
        res.setUserProfile(dto.getUserProfile());
        res.setUserEmail(dto.getUserEmail());
        res.setUserExp(dto.getUserExp());

        int level = calculateLevel(dto.getUserExp() != null ? dto.getUserExp() : 0L);
        res.setUserLevel(level);
        res.setUserLevelName(getLevelName(level));

        return res;
    }

    private static int calculateLevel(long totalExp) {
        int level = 1;
        long remaining = totalExp;
        while (level < 100) {
            long required = 100L + ((long) level - 1L) * 20L;
            if (remaining < required) break;
            remaining -= required;
            level++;
        }
        return level;
    }

    private static String getLevelName(int level) {
        if (level >= 100) return "이음";
        if (level >= 90) return "수어 마스터";
        if (level >= 80) return "연결자";
        if (level >= 70) return "숙련가";
        if (level >= 60) return "공감가";
        if (level >= 50) return "표현가";
        if (level >= 40) return "소통가";
        if (level >= 30) return "실천가";
        if (level >= 20) return "학습자";
        if (level >= 10) return "새싹 수어인";
        return "입문자";
    }
}
