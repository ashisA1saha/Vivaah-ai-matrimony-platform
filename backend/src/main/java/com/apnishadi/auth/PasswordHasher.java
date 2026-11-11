package com.apnishadi.auth;

import org.springframework.stereotype.Component;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component public class PasswordHasher {
 private static final int ITERATIONS=120000, KEY_LENGTH=256; private static final SecureRandom RANDOM=new SecureRandom();
 public String hash(String password) { byte[] salt=new byte[16]; RANDOM.nextBytes(salt); return ITERATIONS+":"+enc(salt)+":"+enc(pbkdf2(password.toCharArray(),salt,ITERATIONS)); }
 public boolean matches(String password,String stored) { try { String[] p=stored.split(":"); if(p.length!=3)return false; byte[] salt=Base64.getDecoder().decode(p[1]); byte[] expected=Base64.getDecoder().decode(p[2]); return java.security.MessageDigest.isEqual(expected,pbkdf2(password.toCharArray(),salt,Integer.parseInt(p[0]))); } catch(RuntimeException e) { return false; } }
 private static byte[] pbkdf2(char[] p,byte[] s,int iterations) { try { PBEKeySpec spec=new PBEKeySpec(p,s,iterations,KEY_LENGTH); return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded(); } catch(NoSuchAlgorithmException|InvalidKeySpecException e) { throw new IllegalStateException("Password hashing failed",e); } }
 private static String enc(byte[] b){return Base64.getEncoder().encodeToString(b);}
}
