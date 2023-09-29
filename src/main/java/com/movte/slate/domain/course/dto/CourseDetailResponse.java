package com.movte.slate.domain.course.dto;


import com.movte.slate.domain.course.domain.Course;
import java.util.Comparator;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseDetailResponse {

    private Long courseId;
    private String title;
    private String subTitle;
    private String thumbnailImageUrl;
    private List<CourseImageResponse> courseImageResponses;

    @Builder
    public CourseDetailResponse(Long courseId, String title, String subTitle,
        String thumbnailImageUrl,
        List<CourseImageResponse> courseImageResponses) {
        this.courseId = courseId;
        this.title = title;
        this.subTitle = subTitle;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.courseImageResponses = courseImageResponses;
    }

    public static CourseDetailResponse from(Course course) {
        return CourseDetailResponse.builder()
            .courseId(course.getId())
            .title(course.getTitle())
            .subTitle(course.getSubTitle())
            .thumbnailImageUrl(course.getThumbnailImageUrl())
            .courseImageResponses(
                course.getCourseImages().stream().map(CourseImageResponse::from)
                    .sorted((Comparator.comparingInt(CourseImageResponse::getOrder))).toList())
            .build();
    }
}
