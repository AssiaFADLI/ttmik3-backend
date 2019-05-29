package com.viseo.ttmik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {

    @PositiveOrZero
    private int number;

    @NotEmpty
    private String title;

    @NotEmpty
    private String summary;

}
