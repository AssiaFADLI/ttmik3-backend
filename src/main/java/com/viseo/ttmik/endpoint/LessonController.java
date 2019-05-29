package com.viseo.ttmik.endpoint;


import com.viseo.ttmik.dto.LessonDto;
import com.viseo.ttmik.exception.LessonException;
import com.viseo.ttmik.exception.Message;
import com.viseo.ttmik.exception.TtmikMessageException;
import com.viseo.ttmik.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(LessonController.PATH)
@RequiredArgsConstructor
@Validated
@Slf4j
public class LessonController {
    public static final String PATH = "/ttmik/lessons";

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity createLesson(@RequestBody @Valid LessonDto dto) {
        try {
            lessonService.createLesson(dto);
            return ResponseEntity.ok().build();
        } catch (LessonException e) {
            return ResponseEntity.badRequest().body(
                    TtmikMessageException.build(e.getReason(), "lesson", e.getLesson())
            );
        }
    }
}
