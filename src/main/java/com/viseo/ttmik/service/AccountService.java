package com.viseo.ttmik.service;

import com.viseo.ttmik.dto.AccountDto;
import com.viseo.ttmik.exception.AccountException;
import com.viseo.ttmik.mapper.AccountMapper;
import com.viseo.ttmik.model.AccountEntity;
import com.viseo.ttmik.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void createAccount(AccountDto dto) throws AccountException {
        Optional<AccountEntity> optionalEntity = accountRepository.findById(dto.getLogin());
        if (optionalEntity.isPresent()) {
            throw new AccountException(dto.getLogin(), "The account with the given login already exists");
        }

        AccountEntity entity = AccountMapper.INSTANCE.toEntity(dto);
        accountRepository.save(entity);

    }

    public List<AccountDto> getAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(entity -> AccountMapper.INSTANCE.toDto(entity))
                .collect(Collectors.toList());
    }
}
