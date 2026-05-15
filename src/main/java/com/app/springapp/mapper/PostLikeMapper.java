package com.app.springapp.mapper;

import com.app.springapp.domain.vo.PostLikeVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostLikeMapper {
//    게시글 좋아요 넣기
    public void insert(PostLikeVO postLikeVO);

//    게시글 좋아요 삭제
    public void deleteByUserIdAndPostId(PostLikeVO postLikeVO);
}
