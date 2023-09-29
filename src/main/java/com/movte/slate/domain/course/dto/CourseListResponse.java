package com.movte.slate.domain.course.dto;

import com.movte.slate.domain.course.domain.Course;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseListResponse {

    private Long courseId;
    private String title;
    private String subTitle;
    private String thumbnailImageUrl;

    @Builder
    private CourseListResponse(Long courseId, String title, String subTitle,
        String thumbnailImageUrl) {
        this.courseId = courseId;
        this.title = title;
        this.subTitle = subTitle;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public static CourseListResponse from(Course course) {
        return CourseListResponse.builder()
            .courseId(course.getId())
            .title(course.getTitle())
            .subTitle(course.getSubTitle())
            .thumbnailImageUrl(course.getThumbnailImageUrl())
            .build();
    }

}
