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
}
