package com.app.springapp.api;

import com.app.springapp.domain.dto.UserDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/follow")
public class PrivateFollowApi {
    private final FollowService followService;

//    유저 팔로우 하기
    @GetMapping("/{followingId}")
    @Operation(summary = "유저 팔로우", description = "유저를 팔로우 하는 서비스")
    @ApiResponse(responseCode = "200", description = "유저 팔로우 성공")
    @ApiResponse(responseCode = "400", description = "유저 팔로우 실패")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @ApiResponse(responseCode = "404", description = "잘못된 요청입니다")
    @Parameter(
            name = "followingId",
            description = "팔로우 할 유저 id",
            example = "1",
            required = true,
            in = ParameterIn.PATH,
            schema = @Schema(type = "number")
    )
    public ResponseEntity<ApiResponseDTO> followUser(
            @PathVariable Long followingId,
            Authentication authentication
    ){
        Long userId = ((UserDTO)  authentication.getPrincipal()).getId();
        followService.userFollow(userId,followingId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(true, "유저 팔로우 성공"));
    }

//    유저 팔로우 취소하기
    @DeleteMapping("/cancel/{followingId}")
    @Operation(summary = "유저 팔로우 취소", description = "유저를 팔로우 취소 하는 서비스")
    @ApiResponse(responseCode = "200", description = "유저 팔로우 취소 성공")
    @ApiResponse(responseCode = "400", description = "유저 팔로우 취소 실패")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @ApiResponse(responseCode = "404", description = "잘못된 요청입니다")
    @Parameter(
            name = "followingId",
            description = "팔로우 취소 할 유저 id",
            example = "1",
            required = true,
            in = ParameterIn.PATH,
            schema = @Schema(type = "number")
    )
    public ResponseEntity<ApiResponseDTO> cancelFollow(
            @PathVariable Long followingId,
            Authentication authentication
    ){
        Long userId = ((UserDTO)  authentication.getPrincipal()).getId();
        followService.cancelFollow(userId,followingId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(true, "유저 팔로우 취소 성공"));
    }
}
