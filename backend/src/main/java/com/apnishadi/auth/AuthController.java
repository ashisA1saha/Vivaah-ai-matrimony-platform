package com.apnishadi.auth;

import com.apnishadi.profile.Profile;
import com.apnishadi.profile.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AccountRepository accounts; private final ProfileRepository profiles; private final PasswordHasher hasher; private final JwtService jwtService; private final AccountDetailsService details;
 public AuthController(AccountRepository a, ProfileRepository p, PasswordHasher h, JwtService js, AccountDetailsService ds) { accounts=a; profiles=p; hasher=h; jwtService=js; details=ds; }
 @Transactional @PostMapping("/signup") public AuthResponse signup(@Valid @RequestBody SignupRequest request) {
  String email=request.email().trim().toLowerCase(); if(accounts.existsByEmailIgnoreCase(email)) throw new ResponseStatusException(BAD_REQUEST,"Email is already registered");
  Profile profile=request.profile(); profile.setId(null); Profile saved=profiles.save(profile); Account account=new Account(); account.setEmail(email); account.setPhone(request.phone().trim()); account.setPasswordHash(hasher.hash(request.password())); account.setProfile(saved); account.setRole("USER"); accounts.save(account);
  AccountPrincipal principal=(AccountPrincipal)details.loadUserByUsername(email); return new AuthResponse(jwtService.generate(principal),saved);
 }
 @GetMapping("/me") public Profile me(){ return accounts.findByEmailIgnoreCase(CurrentAccount.principal().getUsername()).orElseThrow().getProfile(); }
 @PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest request) {
  Account account=accounts.findByEmailIgnoreCase(request.email().trim().toLowerCase()).orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.UNAUTHORIZED,"Invalid email or password"));
  if(!hasher.matches(request.password(),account.getPasswordHash())) throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.UNAUTHORIZED,"Invalid email or password");
  AccountPrincipal principal=(AccountPrincipal)details.loadUserByUsername(account.getEmail()); return new AuthResponse(jwtService.generate(principal),account.getProfile());
 }
}
