package com.movte.slate.domain.course.application;


import com.movte.slate.domain.course.domain.Course;
import com.movte.slate.domain.course.dto.CourseDetailResponse;
import com.movte.slate.domain.course.dto.CourseListResponse;
import com.movte.slate.domain.course.repository.CourseRepository;
import com.movte.slate.global.exception.NotFoundException;
import com.movte.slate.global.exception.NotFoundExceptionCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseSearchUseCase {

    private final CourseRepository courseRepository;


    public List<CourseListResponse> getList() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseListResponse::from).toList();
    }

    public CourseDetailResponse getDetail(Long courseId) {
        Course course = courseRepository.selectCourseByIdWithImages(courseId)
            .orElseThrow(() -> new NotFoundException(NotFoundExceptionCode.NOT_FOUND_COURSE));
        return CourseDetailResponse.from(course);
    }

}
