package com.movte.slate.domain.snapshot.api;

import com.movte.slate.domain.snapshot.application.service.SearchBunchOfSceneWithMovieTitleService;
import com.movte.slate.domain.snapshot.application.service.response.SearchBunchOfSceneWithMovieTitleServiceResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class SceneApi {
    private final SearchBunchOfSceneWithMovieTitleService searchBunchOfSceneWithMovieTitleService;

    @GetMapping(value = "/scene", params="title")
    public ResponseEntity<SuccessResponse<SearchBunchOfSceneWithMovieTitleServiceResponse>>
    searchBunchOfSceneWithMovieTitle(@RequestParam("title") String title, HttpServletRequest request) {
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        SearchBunchOfSceneWithMovieTitleServiceResponse searchBunchOfSceneWithMovieTitleServiceResponse =
                searchBunchOfSceneWithMovieTitleService.searchBunchOfSceneWithMovieTitle(title);
        return ResponseFactory.success(searchBunchOfSceneWithMovieTitleServiceResponse);
    }
}
