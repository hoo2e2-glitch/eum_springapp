package com.app.springapp.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "댓글 작성 요청 DTO")
public class CommentRequestDTO {
    @Schema(description = "댓글 내용", example = "좋은 글이네요!", required = true)
    private String commentContent;
//    @Schema(description = "게시글 번호", example = "1", required = true)
//    private Long postId;
//    @Schema(description = "유저 번호", example = "1", required = true)
//    private Long userId;
//    @Schema(description = "부모 댓글 번호 (대댓글인 경우)", example = "1")
//    private Long commentId;
}
