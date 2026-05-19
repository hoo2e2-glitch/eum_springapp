package com.app.springapp.mapper;

import com.app.springapp.domain.dto.CommentDTO;
import com.app.springapp.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public List<CommentDTO> selectAllByPostId(Long postId);

//    유저가 작성한 댓글 불러오기
    public List<CommentDTO> selectAllByUserId(Long userId);

    public void insert(CommentVO commentVO);

    public int existByIdAndPostId(CommentVO commentVO);

    public int existByIdAndUserId(CommentVO commentVO);

    public void update(CommentVO commentVO);

    public void updateIsDeleted(CommentVO commentVO);

    public void updateRepliesIsDeleted(Long commentId);

    public int isParentComment(Long commentId);
}
