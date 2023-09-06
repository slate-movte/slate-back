package com.movte.slate.domain.snapshot.api;

import static org.springframework.http.MediaType.*;

import com.movte.slate.domain.snapshot.application.service.SnapShotService;
import com.movte.slate.domain.snapshot.application.service.request.InsertSnapShotServiceRequest;
import com.movte.slate.domain.snapshot.application.service.response.InsertSnapShotServiceResponse;
import com.movte.slate.global.response.ResponseFactory;
import com.movte.slate.global.response.SuccessResponse;
import com.movte.slate.jwt.domain.JwtToken;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class SnapShotApi {

    private final SnapShotService snapShotService;

    @PostMapping(value = "/snapshot", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<SuccessResponse<InsertSnapShotServiceResponse>> insertSnapshot(@RequestPart("snapshot") List<MultipartFile> snapshot, @RequestPart("sceneId") long sceneId, HttpServletRequest request){
        JwtToken accessToken = (JwtToken) request.getAttribute("accessToken");
        Long userId = accessToken.getUserId();
        InsertSnapShotServiceResponse insertSnapShotServiceResponse = snapShotService.insertSnapshot(userId, new InsertSnapShotServiceRequest(sceneId, snapshot.get(0)));
        return ResponseFactory.success(insertSnapShotServiceResponse);
    }
}
