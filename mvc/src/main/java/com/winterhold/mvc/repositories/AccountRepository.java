package com.winterhold.mvc.repositories;

import com.winterhold.mvc.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
