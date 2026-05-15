package com.app.springapp.repository;

import com.app.springapp.domain.vo.PostLikeVO;
import com.app.springapp.mapper.PostLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeDAO {
    private final PostLikeMapper postLikeMapper;

//    게시글 좋아요 쿼리
    public void save(PostLikeVO postLikeVO){
        postLikeMapper.insert(postLikeVO);
    }

}
