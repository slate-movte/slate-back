package com.movte.slate.domain.stillcut.application.service;

import com.movte.slate.domain.stillcut.application.service.dto.StillCutResponseDto;
import com.movte.slate.domain.stillcut.application.service.response.SearchBunchOfSnapshotOfOwnerServiceResponse;
import com.movte.slate.domain.stillcut.domain.StillCut;
import com.movte.slate.domain.stillcut.repository.FindStillCutByUserPort;
import com.movte.slate.domain.user.domain.User;
import com.movte.slate.domain.user.repository.FindUserByIdPort;
import com.movte.slate.global.exception.UnauthorizedException;
import com.movte.slate.global.exception.UnauthorizedExceptionCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchUserIdService {
    private final FindUserByIdPort findUserByIdPort;
    private final FindStillCutByUserPort findStillCutByUserPort;

    public SearchBunchOfSnapshotOfOwnerServiceResponse searchUserId(long userId, long findUserId) {
        Optional<User> userOpt = findUserByIdPort.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_USER);
        }
        User user = userOpt.get();
        Optional<User> idUserOpt = findUserByIdPort.findById(findUserId);
        if (idUserOpt.isEmpty()) {
            throw new UnauthorizedException(UnauthorizedExceptionCode.NOT_USER);
        }
        User findUser = idUserOpt.get();
        List<StillCut> userStillCuts = findStillCutByUserPort.findByUser(findUser);
        List<StillCutResponseDto> stillCutResponseDtos = new ArrayList<>();
        for(StillCut stillCut : userStillCuts){
            stillCutResponseDtos.add(new StillCutResponseDto(stillCut.getStillCutId(),
                    stillCut.getImageUrl()));
        }
        return SearchBunchOfSnapshotOfOwnerServiceResponse.builder().scenes(stillCutResponseDtos).build();
    }
}
