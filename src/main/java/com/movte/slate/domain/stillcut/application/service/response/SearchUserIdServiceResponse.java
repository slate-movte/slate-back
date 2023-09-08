package com.movte.slate.domain.stillcut.application.service.response;

import com.movte.slate.domain.stillcut.application.service.dto.StillCutResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SearchUserIdServiceResponse {
    List<StillCutResponseDto> scenes;

}
