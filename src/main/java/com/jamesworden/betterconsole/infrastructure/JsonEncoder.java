package com.jamesworden.betterconsole.infrastructure;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.Encoder;

public class JsonEncoder implements Encoder.Text<String>{
	@Override
	public String encode(String message) throws EncodeException {
		return Json.createObjectBuilder()
				.add("message",message)
				.build()
				.toString();
	}

	@Override
	public void init(EndpointConfig endpointConfig) {

	}

	@Override
	public void destroy() {

	}
}
