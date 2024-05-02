package org.schweben.dinghyapi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerUtils {

	@Value("${server.address}")
	private String serverAddress;

	@Value("${server.port}")
	private String serverPort;

	@Value("${server.protocol}")
	private String serverProtocol;

	public String getServerAddress() {
		return serverProtocol + "://" + serverAddress + ":" + serverPort;
	}
}
