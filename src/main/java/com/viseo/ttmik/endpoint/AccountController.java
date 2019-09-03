package com.viseo.ttmik.endpoint;

import com.viseo.ttmik.dto.AccountDto;
import com.viseo.ttmik.exception.AccountException;
import com.viseo.ttmik.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.viseo.ttmik.exception.TtmikMessageException.build;

@RestController
@RequestMapping(AccountController.PATH)
@RequiredArgsConstructor
@Validated
@Slf4j
public class AccountController {
    public static final String PATH = "/ttmik/accounts";

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto dto){
        try {
            accountService.createAccount(dto);
            return ResponseEntity.ok().build();
        } catch (AccountException e) {
            return ResponseEntity.badRequest().body(
                    build(e.getReason(), "account", e.getAccount())
            );
        }
    }

    @GetMapping
    public List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }
}
