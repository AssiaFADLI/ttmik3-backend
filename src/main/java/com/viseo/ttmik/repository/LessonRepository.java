package com.viseo.ttmik.repository;

import com.viseo.ttmik.model.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonEntity, Integer> {
}
