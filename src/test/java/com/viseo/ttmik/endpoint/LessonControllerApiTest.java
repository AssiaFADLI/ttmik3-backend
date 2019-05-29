package com.viseo.ttmik.endpoint;

import com.viseo.ttmik.dto.LessonDto;
import com.viseo.ttmik.exception.LessonException;
import com.viseo.ttmik.service.LessonService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * API test on @{@link LessonController}
 */
@WebMvcTest(LessonController.class)
public class LessonControllerApiTest extends AbstractControllerTest{

    @MockBean
    private LessonService lessonService;

   @Test // erreur 404
    void should_throw_validation_exception_on_invalid_body() throws Exception {
        // when
        mvc.perform(post(LessonController.PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LessonDto.builder().number(-1).build()))
        )
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[*].message",
                        containsInAnyOrder("must be greater than or equal to 0", "must not be empty", "must not be empty")
                ))
                .andExpect(jsonPath("$.messages[*].params.propertyPath",
                        containsInAnyOrder(
                                "number", "title", "summary"
                        )));
    }

   @Test
    void should_throw_exception_when_failed_to_create_lesson() throws Exception {
        // given
        Mockito.doThrow(new LessonException(1, "The lesson with the given number already exists"))
                .when(lessonService).createLesson(ArgumentMatchers.any(LessonDto.class));

        // when
        mvc.perform(post(LessonController.PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createLessonDto()))
        )
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[*].message",
                        contains("The lesson with the given number already exists")
                ))
                .andExpect(jsonPath("$.messages[*].params.lesson",
                        contains(1)
                ));
    }

   @Test
   void should_create_lesson() throws Exception {
       // when
       mvc.perform(post(LessonController.PATH)
               .contentType(APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(createLessonDto()))
       )
               // then
               .andExpect(status().isOk());
   }

    private static LessonDto createLessonDto() {
        return new LessonDto(1, "my-title", "my-summary");
    }
}

