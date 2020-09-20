package com.jamesworden.betterconsole.application;

import org.bukkit.Bukkit;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class BCServer extends WebSocketServer {

	Logger LOGGER = Bukkit.getLogger();

	public BCServer(int port) {
		super(new InetSocketAddress(port));
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		conn.send("Welcome!");
		LOGGER.info("Client with address " + conn.getRemoteSocketAddress() + " has connected!");
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		LOGGER.info("Client with address " + conn.getRemoteSocketAddress() + " has disconnected!");
	}

	@Override
	public void onMessage(WebSocket conn, String message) {

	}

	@Override
	public void onError(WebSocket conn, Exception ex) {

	}

	@Override
	public void onStart() {
		LOGGER.info("BetterConsole server started on port " + getPort() + "!");
	}
}
