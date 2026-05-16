package com.app.springapp.mapper;

import com.app.springapp.domain.dto.ChatRoomDTO;
import com.app.springapp.domain.vo.ChatRoomVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomMapper {
//    채팅방 방 목록 불러와주기
    public List<ChatRoomDTO> selectAll();

//    채팅방 생성
    public void insert(ChatRoomVO chatRoomVO);

//    채팅방 수정
}
