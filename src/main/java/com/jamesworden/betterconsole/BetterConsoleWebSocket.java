package com.jamesworden.betterconsole;

import org.bukkit.Bukkit;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class BetterConsoleWebSocket {

	@OnWebSocketConnect
	public void onConnect(Session session) throws Exception {
		BetterConsoleServer.addSession(session);
		Bukkit.getLogger().info("BetterConsole client connected!");
	}

	@OnWebSocketClose
	public void onClose(Session session, int statusCode, String reason) throws Exception {
		BetterConsoleServer.removeSession(session);
		Bukkit.getLogger().info("BetterConsole client disconnected!");
	}

	@OnWebSocketMessage
	public void onMessage(Session session, String message) throws Exception {
		session.getRemote().sendString(message);
		Bukkit.getLogger().info(message);
	}

}
