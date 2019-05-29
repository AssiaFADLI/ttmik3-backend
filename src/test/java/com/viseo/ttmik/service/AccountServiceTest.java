package com.viseo.ttmik.service;

import com.viseo.ttmik.dto.AccountDto;
import com.viseo.ttmik.exception.AccountException;
import com.viseo.ttmik.model.AccountEntity;
import com.viseo.ttmik.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;

    @Test
    void should_create_account() throws AccountException {
        // given
        AccountDto dto = createAccountDto();

        // When
        accountService.createAccount(dto);

        //then
        Mockito.verify(accountRepository).save(argThat(a ->
                a.getLogin().equals("login") &&
                        a.getEmail().equals("email") &&
                        a.getPassword().equals("password")
        ));
    }

    @Test
    void should_throw_exception_on_existing_account() {
        // given
        AccountDto dto = createAccountDto();
        AccountEntity entity = createAccountEntity();

        Mockito.when(accountRepository.findById("login")).thenReturn(Optional.of(entity));

        // When
        Throwable throwable = catchThrowable(() -> accountService.createAccount(dto));

        // Then
        assertThat(throwable).isNotNull()
                .isInstanceOf(AccountException.class)
                .hasMessage("The account with the given login already exists");
    }

    private static AccountEntity createAccountEntity() {
        return new AccountEntity("login", "email", "password");
    }

    private static AccountDto createAccountDto() {
        return new AccountDto("login", "email", "password");
    }
}

