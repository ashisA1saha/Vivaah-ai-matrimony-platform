package com.apnishadi.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accounts;
    public AccountDetailsService(AccountRepository accounts) { this.accounts = accounts; }
    @Override public UserDetails loadUserByUsername(String email) {
        Account a = accounts.findByEmailIgnoreCase(email).orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        return new AccountPrincipal(a.getId(), a.getProfile().getId(), a.getEmail(), a.getPasswordHash(), a.getRole() == null ? "USER" : a.getRole());
    }
}
