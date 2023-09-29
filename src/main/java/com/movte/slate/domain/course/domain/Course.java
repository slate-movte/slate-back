package com.movte.slate.domain.course.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, name = "sub_title")
    private String subTitle;

    @Column(nullable = false, name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseImage> courseImages = new ArrayList<>();

    @Builder
    public Course(Long id, String title, String subTitle) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
    }
}
