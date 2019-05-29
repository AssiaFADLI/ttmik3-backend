package com.viseo.ttmik.endpoint;

import com.viseo.ttmik.dto.AccountDto;
import com.viseo.ttmik.exception.AccountException;
import com.viseo.ttmik.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * API test on @{@link AccountController}
 */
@WebMvcTest(AccountController.class)
class AccountControllerApiTest extends AbstractControllerTest {

    @MockBean
    private AccountService accountService;

    @Test
    void should_throw_validation_exception_on_invalid_body() throws Exception {
        // when
        mvc.perform(post(AccountController.PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new AccountDto()))
        )
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[*].message",
                        containsInAnyOrder("must not be empty", "must not be empty", "must not be empty")
                ))
                .andExpect(jsonPath("$.messages[*].params.propertyPath",
                        containsInAnyOrder(
                                "login", "password", "email"
                        )));
    }

    @Test
    void should_throw_exception_when_failed_to_create_account() throws Exception {
        // given
        Mockito.doThrow(new AccountException("my-login", "The account with the given login already exists"))
                .when(accountService).createAccount(ArgumentMatchers.any(AccountDto.class));

        // when
        mvc.perform(post(AccountController.PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAccountDto()))
        )
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[*].message",
                        contains("The account with the given login already exists")
                ))
                .andExpect(jsonPath("$.messages[*].params.account",
                        contains("my-login")
                ));
    }

    @Test
    void should_create_account() throws Exception {
        // when
        mvc.perform(post(AccountController.PATH)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAccountDto()))
        )
                // then
                .andExpect(status().isOk());
    }

    private static AccountDto createAccountDto() {
        return new AccountDto("my-login", "my-email", "my-password");
    }
}
