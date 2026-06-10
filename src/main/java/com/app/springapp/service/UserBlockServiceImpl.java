package com.app.springapp.service;

import com.app.springapp.domain.vo.UserBlockVO;
import com.app.springapp.exception.UserBlockException;
import com.app.springapp.repository.UserBlockDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserBlockServiceImpl implements UserBlockService {
    private final UserBlockDAO userBlockDAO;

    //    유저 블록 서비스
    @Override
    public void blockUser(Long userId, Long blockingId) {
//        자기 자신 블락 못함
        if(userId == blockingId){
            throw new UserBlockException("잘못된 요청입니다", HttpStatus.BAD_REQUEST);
        }

        UserBlockVO userBlockVO = new UserBlockVO();
        userBlockVO.setBlockerId(userId);
        userBlockVO.setBlockingId(blockingId);

        try {
            userBlockDAO.save(userBlockVO);
        } catch (UserBlockException e) {
            throw new UserBlockException("유저 블락 실패", HttpStatus.BAD_REQUEST);
        }
    }
}
