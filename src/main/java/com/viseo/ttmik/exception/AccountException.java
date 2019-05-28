package com.viseo.ttmik.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder(value = {"reason", "account"})
public class AccountException extends TtmikBaseException{
    private final String account;

    public AccountException(String account, String reason) {
        super(reason);
        this.account = account;
    }
}
