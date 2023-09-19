package com.movte.slate.domain.snapshot.api;

import com.movte.slate.domain.snapshot.application.service.SearchBunchOfSceneWithMovieTitleService;
import com.movte.slate.domain.snapshot.application.service.SearchSceneUseCase;
import com.movte.slate.domain.snapshot.application.service.response.SearchBunchOfSceneWithMovieTitleServiceResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class SceneApi {

    private final SearchBunchOfSceneWithMovieTitleService searchBunchOfSceneWithMovieTitleService;
    private final SearchSceneUseCase sceneUseCase;

    @GetMapping(value = "/scene", params = "title")
    public ResponseEntity<SuccessResponse<SearchBunchOfSceneWithMovieTitleServiceResponse>>
    searchBunchOfSceneWithMovieTitle(@RequestParam("title") String title) {
        SearchBunchOfSceneWithMovieTitleServiceResponse searchBunchOfSceneWithMovieTitleServiceResponse =
            searchBunchOfSceneWithMovieTitleService.searchBunchOfSceneWithMovieTitle(title);
        return ResponseFactory.success(searchBunchOfSceneWithMovieTitleServiceResponse);
    }

    @GetMapping("/search/scene/{id}")
    public ResponseEntity<?> searchSceneDetail(@PathVariable(name = "id") Long id) {
        return ResponseFactory.success(sceneUseCase.searchDetail(id));
    }
}
