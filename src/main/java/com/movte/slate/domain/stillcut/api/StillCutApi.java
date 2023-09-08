package com.movte.slate.domain.stillcut.api;

import com.movte.slate.domain.stillcut.application.service.SearchMovieTitleService;
import com.movte.slate.domain.stillcut.application.service.response.SearchMovieTitleServiceResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StillCutApi {

    private final SearchMovieTitleService searchMovieTitleService;

    @GetMapping(value = "/stillcut")
    public ResponseEntity<SuccessResponse<SearchMovieTitleServiceResponse>> searchMovieTitle(@RequestParam("title") String title, HttpServletRequest request) {
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        Long userId = accessToken.getUserId();
        SearchMovieTitleServiceResponse searchMovieTitleServiceResponse = searchMovieTitleService.searchMovieTitle(title);
        return ResponseFactory.success(searchMovieTitleServiceResponse);
    }

}
