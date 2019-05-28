package com.viseo.ttmik.exception;

import lombok.Getter;

@Getter
public class TtmikBaseException extends Exception{
    private final String reason;

    public TtmikBaseException(String reason) {
        super(reason);
        this.reason = reason;
    }
}
