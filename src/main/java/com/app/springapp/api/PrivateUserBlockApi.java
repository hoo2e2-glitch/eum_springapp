package com.app.springapp.api;

import com.app.springapp.domain.dto.UserDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.UserBlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/user-block")
public class PrivateUserBlockApi {
    private final UserBlockService userBlockService;

//    블락 하는 것
    @GetMapping("/{blockingId}")
    @Operation(summary = "유저 차단", description = "유저를 차단하는 서비스")
    @ApiResponse(responseCode = "200", description = "유저 차단 성공")
    @ApiResponse(responseCode = "400", description = "유저 차단 실패")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @ApiResponse(responseCode = "404", description = "잘못된 요청입니다")
    @Parameter(
            name = "blockingId",
            description = "차단할 유저 id",
            example = "1",
            required = true,
            in = ParameterIn.PATH,
            schema = @Schema(type = "number")
    )
    public ResponseEntity<ApiResponseDTO> blockUser(
            @PathVariable Long blockingId,
            Authentication  authentication
    ){
        Long userId = ((UserDTO) authentication.getPrincipal()).getId();
        userBlockService.blockUser(userId,blockingId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseDTO.of(true, "유저 차단 성공"));
    }
}
