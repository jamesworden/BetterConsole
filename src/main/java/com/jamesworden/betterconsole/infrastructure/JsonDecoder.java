package com.jamesworden.betterconsole.infrastructure;

import javax.json.Json;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class JsonDecoder implements Decoder.Text<String> {
	@Override
	public String decode(String message) throws DecodeException {
		return Json.createReader(new StringReader(message))
				.readObject()
				.getString("message");
	}

	@Override
	public boolean willDecode(String s) {
		return false;
	}

	@Override
	public void init(EndpointConfig endpointConfig) {

	}

	@Override
	public void destroy() {

	}
}
