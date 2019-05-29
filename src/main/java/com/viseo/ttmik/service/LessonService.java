package com.viseo.ttmik.service;

import com.viseo.ttmik.dto.LessonDto;
import com.viseo.ttmik.exception.LessonException;
import com.viseo.ttmik.mapper.LessonMapper;
import com.viseo.ttmik.model.LessonEntity;
import com.viseo.ttmik.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    @Transactional
    public void createLesson (LessonDto dto) throws LessonException {
        Optional<LessonEntity> optionalEntity = lessonRepository.findById(dto.getNumber());
        if(optionalEntity.isPresent()) {
            throw new LessonException(dto.getNumber(),"The lesson with the given number already exists");
        }
        LessonEntity entity = LessonMapper.INSTANCE.toEntity(dto);
        lessonRepository.save(entity);
    }
}
