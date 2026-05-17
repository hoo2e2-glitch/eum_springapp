package com.app.springapp.domain.vo;

import com.app.springapp.domain.dto.request.CommentRequestDTO;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Data
public class CommentVO {
    private Long id;
    private String commentContent;
    private LocalDateTime commentCreateAt;
    private boolean commentIsDeleted;
    private Long postId;
    private Long userId;
    private Long commentId;

    public static CommentVO from(CommentRequestDTO commentRequestDTO) {
        CommentVO commentVO = new CommentVO();
        commentVO.commentContent = commentRequestDTO.getCommentContent();

        return commentVO;
    }
}
