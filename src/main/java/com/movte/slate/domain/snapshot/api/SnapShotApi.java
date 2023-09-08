package com.movte.slate.domain.snapshot.api;

import com.movte.slate.domain.snapshot.application.service.SearchBunchOfSnapshotOfOwnerService;
import com.movte.slate.domain.snapshot.application.service.SearchBunchOfSnapshotWithMovieTitleService;
import com.movte.slate.domain.snapshot.application.service.SnapShotService;
import com.movte.slate.domain.snapshot.application.service.request.InsertSnapShotServiceRequest;
import com.movte.slate.domain.snapshot.application.service.response.InsertSnapShotServiceResponse;
import com.movte.slate.domain.snapshot.application.service.response.SearchBunchOfSnapshotOfOwnerServiceResponse;
import com.movte.slate.domain.snapshot.application.service.response.SearchBunchOfSnapshotWithMovieTitleServiceResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
public class SnapShotApi {

    private final SnapShotService snapShotService;
    private final SearchBunchOfSnapshotWithMovieTitleService searchBunchOfSnapshotWithMovieTitleService;
    private final SearchBunchOfSnapshotOfOwnerService searchBunchOfSnapshotOfOwnerService;

    @PostMapping(value = "/snapshot")
    public ResponseEntity<SuccessResponse<InsertSnapShotServiceResponse>> insertSnapshot(@RequestParam("snapshot") List<MultipartFile> snapshot, @RequestParam("sceneId") long sceneId, HttpServletRequest request) {
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        Long userId = accessToken.getUserId();
        log.info(userId);
        InsertSnapShotServiceResponse insertSnapShotServiceResponse = snapShotService.insertSnapshot(userId, new InsertSnapShotServiceRequest(sceneId, snapshot.get(0)));
        return ResponseFactory.success(insertSnapShotServiceResponse);
    }

    @GetMapping(value = "/snapshot", params="title")
    public ResponseEntity<SuccessResponse<SearchBunchOfSnapshotWithMovieTitleServiceResponse>>
    searchBunchOfSnapshotWithMovieTitle(@RequestParam("title") String title, HttpServletRequest request) {
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        Long userId = accessToken.getUserId(); //todo : 왜 이거 있는지 물어봐야 함.
        SearchBunchOfSnapshotWithMovieTitleServiceResponse searchBunchOfSnapshotWithMovieTitleServiceResponse =
                searchBunchOfSnapshotWithMovieTitleService.searchBunchOfSnapshotWithMovieTitle(title);
        return ResponseFactory.success(searchBunchOfSnapshotWithMovieTitleServiceResponse);
    }

    @GetMapping(value = "/snapshot", params="id")
    public ResponseEntity<SuccessResponse<SearchBunchOfSnapshotOfOwnerServiceResponse>>
    searchBunchOfSnapshotOfOwner(@RequestParam("id") long ownerIdOfSnapshot, HttpServletRequest request) {
        //특정 유저의 snapshot을 찾는 것
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        Long userId = accessToken.getUserId();
        SearchBunchOfSnapshotOfOwnerServiceResponse searchBunchOfSnapshotOfOwnerServiceResponse =
                searchBunchOfSnapshotOfOwnerService.searchBunchOfSnapshotOfOwner(userId, ownerIdOfSnapshot);
        return ResponseFactory.success(searchBunchOfSnapshotOfOwnerServiceResponse);
    }
}
