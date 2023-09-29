package com.movte.slate.domain.course.dto;


import com.movte.slate.domain.course.domain.CourseImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseImageResponse {

    private String imageUrl;
    private int order;

    public CourseImageResponse(String imageUrl, int order) {
        this.imageUrl = imageUrl;
        this.order = order;
    }

    public static CourseImageResponse from(CourseImage image) {
        return new CourseImageResponse(image.getImageUrl(), image.getCourseOrder());
    }
}
