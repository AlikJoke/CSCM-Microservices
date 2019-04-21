package ru.project.cscm.ws_base.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.common.base.Objects;

public final class Sha512Encoder implements PasswordEncoder {

	private static final String DEFAULT_MESSAGE_DIGEST = "SHA-512";
	private static final String DEFAULT_ENCODING = "UTF-16";

	public Sha512Encoder() {
		super();
	}

	private String computeHash(String s, String messageDigest, String encoding) 
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		final MessageDigest alg = MessageDigest.getInstance(messageDigest);
		alg.update(s.getBytes(encoding));
		byte[] raw = alg.digest();
		return Base64.getEncoder().encodeToString(raw);
	}

	@Override
	public String encode(CharSequence rawPassword) {
		try {
			return computeHash(rawPassword.toString(), DEFAULT_MESSAGE_DIGEST, DEFAULT_ENCODING);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return Objects.equal(encode(rawPassword), encodedPassword);
	}
}
