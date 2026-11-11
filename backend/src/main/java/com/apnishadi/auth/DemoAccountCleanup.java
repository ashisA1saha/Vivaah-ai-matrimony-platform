package com.apnishadi.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;

/** Removes legacy demo logins that may remain in an existing local database. */
@Component
@Order(3)
public class DemoAccountCleanup implements CommandLineRunner {
    private static final List<String> LEGACY_DEMO_EMAILS = List.of(
        "aarav@example.com", "ananya@example.com", "kabir@example.com",
        "meera@example.com", "rohan@example.com", "priya@example.com",
        "arjun@example.com", "dev@example.com", "ishaan@example.com",
        "neel@example.com", "samar@example.com", "tara@example.com",
        "nisha@example.com"
    );
    private final AccountRepository accounts;
    public DemoAccountCleanup(AccountRepository accounts) { this.accounts = accounts; }
    @Override public void run(String... args) {
        LEGACY_DEMO_EMAILS.forEach(email -> accounts.findByEmailIgnoreCase(email).ifPresent(accounts::delete));
    }
}
