package com.htrj.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashHelper {

	/**
	 * Create random salt
	 */
	public static String createRandomSalt() {
		byte[] saltBytes = new byte[4];
		SecureRandom random = new SecureRandom();
		random.nextBytes(saltBytes);

		return new String(Base64.encodeBase64(saltBytes));
	}
	public static String computeSaltedHash(String password)
	{
		return computeSaltedHash(password, null);
	}
	/**
	 * Compute salted password hash
	 */
	public static String computeSaltedHash(String password, String salt)
	{
		if(StringUtils.isBlank(salt)){
			salt = "";
		}
		try {
			// Create Byte array of password string
			byte[] secretBytes = password.getBytes("utf-8");
			
			// Create a new salt
			byte[] saltBytes = Base64.decodeBase64(salt.getBytes());
	
			// append the two arrays
			byte[] toHash = new byte[secretBytes.length + saltBytes.length];
			System.arraycopy(secretBytes, 0, toHash, 0, secretBytes.length);
			System.arraycopy(saltBytes, 0, toHash, secretBytes.length, saltBytes.length);
			
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(toHash);
			byte[] computedHash = md.digest();
			
			return new String(Base64.encodeBase64(computedHash));
		} catch (UnsupportedEncodingException ex) {
			// handled
		} catch (NoSuchAlgorithmException ex) {
			// handled
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		String pwd = "user";
		String salt = createRandomSalt();
		
		String hash = computeSaltedHash(pwd, salt);

		System.out.println(salt);
		System.out.println(hash);
	}
}
