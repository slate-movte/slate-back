package com.movte.slate.domain.user.api;

import com.movte.slate.domain.user.application.usecase.CheckDuplicateNicknameUseCase;
import com.movte.slate.global.exception.BadRequestException;
import com.movte.slate.global.exception.BadRequestExceptionCode;
import com.movte.slate.global.response.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CheckDuplicateNicknameApi {
    private final CheckDuplicateNicknameUseCase checkDuplicateNicknameUseCase;

    @GetMapping("/user/nickname/duplicate")
    public ResponseEntity<?> checkIfNicknameIsDuplicate(@RequestParam("nickname") String nickname) {
        boolean isDuplicate = checkDuplicateNicknameUseCase.checkIfNicknameIsDuplicate(nickname);
        if (isDuplicate) {
            return ResponseFactory.fail(new BadRequestException(BadRequestExceptionCode.DUPLICATE_NICKNAME));
        }
        return ResponseFactory.successWithoutData("닉네임이 중복되지 않습니다.");
    }
}
