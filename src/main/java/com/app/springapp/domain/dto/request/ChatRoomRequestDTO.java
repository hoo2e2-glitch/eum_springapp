package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "채팅방 생성 요청 DTO")
public class ChatRoomRequestDTO {
    @Schema(description = "채팅방 이름", example = "수어 학습 모임", required = true)
    private String chatRoomName;
    @Schema(description = "채팅방 타입", example = "그룹", required = true)
    private String chatRoomType;
    @Schema(description = "채팅방 프로필 이미지", example = "default.jpg")
    private String chatRoomProfile;
    @Schema(description = "채팅방 상세 정보", example = "수어 학습 공유방 입니다")
    private String chatRoomDetail;
    @Schema(description = "채팅방 인원 수", example = "100")
    private int ChatRoomLimit;
}
