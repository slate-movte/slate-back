package com.movte.slate.domain.course.repository;

import com.movte.slate.domain.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseQueryRepository {


}
