package com.movte.slate.domain.course.api;


import com.movte.slate.domain.course.application.CourseSearchUseCase;
import com.movte.slate.domain.course.dto.CourseDetailResponse;
import com.movte.slate.domain.course.dto.CourseListResponse;
import com.movte.slate.global.response.ResponseFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourseApi {

    private final CourseSearchUseCase courseSearchUseCase;

    @GetMapping("/course/all")
    public ResponseEntity<?> getList(){
        List<CourseListResponse> result = courseSearchUseCase.getList();
        return ResponseFactory.success(result);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id){
        CourseDetailResponse result = courseSearchUseCase.getDetail(id);
        return ResponseFactory.success(result);
    }
}
