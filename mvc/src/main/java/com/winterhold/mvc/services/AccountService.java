package com.winterhold.mvc.services;

import com.winterhold.mvc.configs.AppSecurityConfig;
import com.winterhold.mvc.dtos.register.RegisterDTO;
import com.winterhold.mvc.models.Account;
import com.winterhold.mvc.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void registerAccount(RegisterDTO dto) {
        PasswordEncoder passwordEncoder = AppSecurityConfig.getPasswordEncoder();
        String hashPassword = passwordEncoder.encode(dto.getPassword());
        Account account = new Account(
                dto.getUsername(),
                hashPassword);
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username tidak ditemukan"));

        return account;
    }
}
