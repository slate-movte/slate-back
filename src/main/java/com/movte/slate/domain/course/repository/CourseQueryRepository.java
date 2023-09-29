package com.movte.slate.domain.course.repository;

import com.movte.slate.domain.course.domain.Course;
import java.util.Optional;

public interface CourseQueryRepository {

    Optional<Course> selectCourseByIdWithImages(Long id);
}
