package com.viseo.ttmik.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class TtmikControllerAdvice {

    /**
     * To handle validation exceptions.
     */
    @ExceptionHandler
    public ResponseEntity<TtmikMessageException> handleValidationFailure(MethodArgumentNotValidException exception) {
        TtmikMessageException.TtmikMessageExceptionBuilder messageExceptionBuilder = TtmikMessageException.builder();

        exception.getBindingResult().getAllErrors().forEach(error ->
                messageExceptionBuilder.message(
                        Message.builder()
                                .message(error.getDefaultMessage())
                                .param("propertyPath", toRejectedValue(error.getCodes()))
                                .build()
                )
        );

        return ResponseEntity.badRequest().body(messageExceptionBuilder.build());
    }

    private static String toRejectedValue(String[] codes) {
        String errorCode = codes[0];
        int index = errorCode.lastIndexOf('.') + 1;
        return errorCode.substring(index);
    }

}
