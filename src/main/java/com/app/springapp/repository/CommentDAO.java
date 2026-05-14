package com.app.springapp.repository;

import com.app.springapp.domain.dto.CommentDTO;
import com.app.springapp.domain.vo.CommentVO;
import com.app.springapp.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDAO {
    private final CommentMapper commentMapper;

//    게시글에 달린 댓글 조회
    public List<CommentDTO> findAllByPostId(Long postId){
        return commentMapper.selectAllByPostId(postId);
    }

//    게시글에 댓글 작성
    public void save(CommentVO commentVO){
        commentMapper.insert(commentVO);
    }
}
