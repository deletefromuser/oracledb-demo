package com.example.oracledbdemo;

import java.security.NoSuchAlgorithmException;

import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

// @SpringBootTest
@Slf4j
class OracledbDemoApplicationTests {

	@Test
	void contextLoads() throws NoSuchAlgorithmException {
		log.info(javax.crypto.Cipher.getMaxAllowedKeyLength("AES") + "");
		StrongTextEncryptor encryptor=	new StrongTextEncryptor();
		encryptor.setPassword("hello");
		log.info(encryptor.encrypt("a12345678"));
	}

}
