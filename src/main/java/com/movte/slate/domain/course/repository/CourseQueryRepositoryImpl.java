package com.movte.slate.domain.course.repository;

import static com.movte.slate.domain.course.domain.QCourse.course;
import static com.movte.slate.domain.course.domain.QCourseImage.courseImage;

import com.movte.slate.domain.course.domain.Course;
import com.movte.slate.domain.course.domain.QCourse;
import com.movte.slate.domain.course.domain.QCourseImage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CourseQueryRepositoryImpl implements CourseQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Course> selectCourseByIdWithImages(Long id) {
        Course findCourse = queryFactory.select(course)
            .from(course)
            .leftJoin(course.courseImages, courseImage)
            .where(course.id.eq(id))
            .fetchOne();
        return Optional.ofNullable(findCourse);
    }
}
