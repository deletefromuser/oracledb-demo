package com.example.oracledbdemo;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

// @SpringBootTest
@Slf4j
class OracledbDemoApplicationTests {

	@Test
	void contextLoads() throws NoSuchAlgorithmException {
		log.info(javax.crypto.Cipher.getMaxAllowedKeyLength("AES") + "");
	}

}
