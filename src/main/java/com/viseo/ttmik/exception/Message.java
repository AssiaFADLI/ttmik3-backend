package com.viseo.ttmik.exception;

import lombok.*;

import java.util.Map;

@ToString
@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class Message {
    private final String message;

    @Singular
    private  final Map<String, String> params;
}
