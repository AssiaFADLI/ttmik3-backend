package com.viseo.ttmik.repository;

import com.viseo.ttmik.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity,String> {
}
