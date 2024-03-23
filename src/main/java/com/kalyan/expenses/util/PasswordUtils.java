package com.kalyan.expenses.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

import io.micrometer.common.util.StringUtils;

@Component
public class PasswordUtils {

	public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		if (salt != null && StringUtils.isNotEmpty(salt))
			digest.update(salt.getBytes());
		byte[] hashBytes = digest.digest(password.getBytes());
		return Base64.getEncoder().encodeToString(hashBytes);
	}

	public static String generateSalt() {
		return Base64.getEncoder().encodeToString(generateByteSecretKey());
	}

	public static byte[] generateByteSecretKey() {
		SecureRandom random = new SecureRandom();
		byte[] keyBytes = new byte[32]; // Adjust the key size as needed
		random.nextBytes(keyBytes);
		return keyBytes;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(generateByteSecretKey());
		System.out.println(hashPassword("sai", generateSalt()));
	}
}
