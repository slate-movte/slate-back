package com.movte.slate.domain.snapshot.api;

import com.movte.slate.domain.snapshot.application.service.SceneService;
import com.movte.slate.domain.snapshot.application.service.SearchBunchOfSceneWithMovieTitleService;
import com.movte.slate.domain.snapshot.application.service.request.InsertSceneServiceRequest;
import com.movte.slate.domain.snapshot.application.service.response.InsertSceneServiceResponse;
import com.movte.slate.domain.snapshot.application.service.response.SearchBunchOfSceneWithMovieTitleServiceResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@RestController
public class SceneApi {
    private final SearchBunchOfSceneWithMovieTitleService searchBunchOfSceneWithMovieTitleService;
    private final SceneService sceneService;

    @GetMapping(value = "/scene", params="title")
    public ResponseEntity<SuccessResponse<SearchBunchOfSceneWithMovieTitleServiceResponse>>
    searchBunchOfSceneWithMovieTitle(@RequestParam("title") String title) {
        SearchBunchOfSceneWithMovieTitleServiceResponse searchBunchOfSceneWithMovieTitleServiceResponse =
                searchBunchOfSceneWithMovieTitleService.searchBunchOfSceneWithMovieTitle(title);
        return ResponseFactory.success(searchBunchOfSceneWithMovieTitleServiceResponse);
    }

    @PostMapping(value = "/scene/new")
    public ResponseEntity<SuccessResponse<InsertSceneServiceResponse>> insertScene(@RequestParam("address") String address,
            @RequestParam("sidoCode") String sidoCode,
            @RequestParam("gugunCode") String gugunCode,
            @RequestParam("latitude") BigDecimal latitude,
            @RequestParam("longitude") BigDecimal longitude,
            @RequestParam("movieTitle") String movieTitle,
            @RequestParam("image") List<MultipartFile> images,
            @RequestParam("sceneDescription") String sceneDescription,
            @RequestParam("sceneLocation") String sceneLocation
            ){
        InsertSceneServiceResponse insertSceneServiceResponse = sceneService.insertScene(new InsertSceneServiceRequest(address, sidoCode, gugunCode, latitude, longitude, movieTitle, images.get(0), sceneDescription,sceneLocation));
        return ResponseFactory.success(insertSceneServiceResponse);
    }

}
