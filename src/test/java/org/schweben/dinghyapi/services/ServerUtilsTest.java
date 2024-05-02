package org.schweben.dinghyapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class ServerUtilsTest {

	private static final String ADDRESS = "banana";
	private static final String PORT = "1234";
	private static final String PROTOCOL = "http";
	private static final String URL = "http://banana:1234";

	private ServerUtils target;

	@BeforeEach
	public void setup() {
		target = new ServerUtils();
		ReflectionTestUtils.setField(target, "serverAddress", ADDRESS);
		ReflectionTestUtils.setField(target, "serverPort", PORT);
		ReflectionTestUtils.setField(target, "serverProtocol", PROTOCOL);
	}

	@Test
	public void whenGetServerAddress_returnAddress() {
		String address = target.getServerAddress();
		Assertions.assertEquals(URL, address);
	}
}
