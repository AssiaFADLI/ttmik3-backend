package com.viseo.ttmik.service;

import com.viseo.ttmik.dto.LessonDto;
import com.viseo.ttmik.exception.LessonException;
import com.viseo.ttmik.model.LessonEntity;
import com.viseo.ttmik.repository.LessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.catchThrowable;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

    @InjectMocks
    private LessonService lessonService;
    @Mock
    private LessonRepository lessonRepository;

   /* @Test
    void should_create_lesson() throws LessonException {
        //given
        LessonDto dto = createLessonDto();
        LessonEntity entity = createLessonEntity();

        //when
        lessonService.createLesson(dto);

        //then
       // Mockito.verify(lessonRepository).save(entity);
        Mockito.verify(lessonRepository).save(argThat(a ->
               a.getNumber().equals(1) &&
                        a.getTitle().equals("title") &&
                        a.getSummary().equals("summary")
        ));
    }
*/
    @Test
    void should_throw_exception_on_existing_lesson(){
        // given
        LessonDto dto = createLessonDto();
        LessonEntity entity = createLessonEntity();

        Mockito.when(lessonRepository.findById(1)).thenReturn(Optional.of(entity));

        // When
        Throwable throwable = catchThrowable(() -> lessonService.createLesson(dto));

        // Then
        assertThat(throwable).isNotNull()
                .isInstanceOf(LessonException.class)
                .hasMessage("The lesson with the given number already exists");
    }

    private static LessonDto createLessonDto(){return new LessonDto(1,"","");}
    private static LessonEntity createLessonEntity(){return  new LessonEntity(1,"title", "summary");}
}

