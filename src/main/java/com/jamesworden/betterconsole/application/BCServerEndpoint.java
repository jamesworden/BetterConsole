package com.jamesworden.betterconsole.application;

import javax.json.Json;
import javax.json.JsonObject;

import com.jamesworden.betterconsole.infrastructure.JsonDecoder;
import com.jamesworden.betterconsole.infrastructure.JsonEncoder;
import org.bukkit.Bukkit;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

@ServerEndpoint(value = "/", encoders = JsonEncoder.class, decoders = JsonDecoder.class)
public class BCServerEndpoint {

	private final Logger LOGGER = Bukkit.getLogger();
	private static Set<Session> sessions;

	@OnOpen
	public void onOpen(final Session session) throws IOException {
		LOGGER.info("Connection opened: " + session);
		sessions.add(session);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		LOGGER.info("Connection closed: " + session + " for reason: " + closeReason);
		sessions.remove(session);
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		LOGGER.info("Received message " + message + " from " + session);
		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable t) {
		try {
			JsonObject jsonObject = Json.createObjectBuilder()
					.add("error", t.getCause().getMessage())
					.build();
			session.getBasicRemote().sendText(jsonObject.toString());

		} catch (IOException e) {

			LOGGER.severe("Internal error: " + e);
		}
	}

	private void broadcast(String message) {
		sessions.parallelStream()
				.filter(Session::isOpen)
				.forEach(s -> sendMessageToSession(message, s));
	}

	private void sendMessageToSession(String message, Session session) {
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
