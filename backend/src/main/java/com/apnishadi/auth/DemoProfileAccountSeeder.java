package com.apnishadi.auth;

import com.apnishadi.profile.Profile;
import com.apnishadi.profile.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class DemoProfileAccountSeeder implements CommandLineRunner {
    private static final String DEMO_PASSWORD = "VivaahDemo@2026";

    private static final List<DemoLogin> DEMO_LOGINS = List.of(
        new DemoLogin("Aarav Mehta", "aarav@vivaah.demo", "+919000000001"),
        new DemoLogin("Ananya Sharma", "ananya@vivaah.demo", "+919000000002"),
        new DemoLogin("Kabir Khan", "kabir@vivaah.demo", "+919000000003"),
        new DemoLogin("Meera Iyer", "meera@vivaah.demo", "+919000000004"),
        new DemoLogin("Rohan Singh", "rohan@vivaah.demo", "+919000000005"),
        new DemoLogin("Priya Nair", "priya@vivaah.demo", "+919000000006"),
        new DemoLogin("Arjun Malhotra", "arjun@vivaah.demo", "+919000000007"),
        new DemoLogin("Dev Patel", "dev@vivaah.demo", "+919000000008"),
        new DemoLogin("Ishaan Rao", "ishaan@vivaah.demo", "+919000000009"),
        new DemoLogin("Neel Bhatia", "neel@vivaah.demo", "+919000000010"),
        new DemoLogin("Samar Verma", "samar@vivaah.demo", "+919000000011"),
        new DemoLogin("Tara Kapoor", "tara@vivaah.demo", "+919000000012"),
        new DemoLogin("Nisha Menon", "nisha@vivaah.demo", "+919000000013")
    );

    private final ProfileRepository profiles;
    private final AccountRepository accounts;
    private final PasswordHasher passwordHasher;

    public DemoProfileAccountSeeder(ProfileRepository profiles, AccountRepository accounts,
                                    PasswordHasher passwordHasher) {
        this.profiles = profiles;
        this.accounts = accounts;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public void run(String... args) {
        for (DemoLogin demo : DEMO_LOGINS) {
            Profile profile = profiles.findByFullName(demo.profileName()).orElse(null);
            if (profile == null) {
                System.out.println("[Vivaah demo] Profile not found: " + demo.profileName());
                continue;
            }

            boolean profileAlreadyHasAccount = accounts.findAll().stream()
                .anyMatch(a -> a.getProfile() != null
                    && a.getProfile().getId().equals(profile.getId()));

            if (profileAlreadyHasAccount || accounts.existsByEmailIgnoreCase(demo.email())) {
                continue;
            }

            Account account = new Account();
            account.setEmail(demo.email());
            account.setPhone(demo.phone());
            account.setPasswordHash(passwordHasher.hash(DEMO_PASSWORD));
            account.setRole("USER");
            account.setProfile(profile);
            accounts.save(account);

            System.out.println("[Vivaah demo] Created account: " + demo.email());
        }
    }

    private record DemoLogin(String profileName, String email, String phone) {}
}
