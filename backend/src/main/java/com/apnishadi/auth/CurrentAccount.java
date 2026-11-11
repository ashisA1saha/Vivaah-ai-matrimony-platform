package com.apnishadi.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public final class CurrentAccount { private CurrentAccount() {}
 public static AccountPrincipal principal() { Authentication a=SecurityContextHolder.getContext().getAuthentication(); if(a==null || !(a.getPrincipal() instanceof AccountPrincipal p)) throw new ResponseStatusException(UNAUTHORIZED,"Authentication required"); return p; }
 public static Long profileId() { return principal().profileId(); }
 public static void requireOwnProfile(Long requested) { if(requested!=null && !profileId().equals(requested)) throw new ResponseStatusException(org.springframework.http.HttpStatus.FORBIDDEN,"You can only access your own account data"); }
}
