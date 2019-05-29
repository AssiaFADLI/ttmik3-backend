package com.viseo.ttmik.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"reason", "account"})
public class LessonException extends TtmikBaseException{
    private final int lesson;

    public LessonException(int lesson, String reason) {
        super(reason);
        this.lesson= lesson;
    }
}

