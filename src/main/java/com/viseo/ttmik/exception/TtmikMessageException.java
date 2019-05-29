package com.viseo.ttmik.exception;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TtmikMessageException {

    @Singular
    private List<Message> messages;

    public  static TtmikMessageException build(String message, String paramKey, Object paramValue) {
        return TtmikMessageException.builder()
                .message(
                        Message.builder()
                                .message(message)
                                .param(paramKey, paramValue)
                                .build()
                )
                .build();
    }
}
