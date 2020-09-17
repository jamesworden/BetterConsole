package com.jamesworden.betterconsole;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocketHandler {

	private String sender, msg;

	@OnWebSocketConnect
	public void onConnect(Session user) throws Exception {
		String username = "User" + BetterConsoleServer.nextUserNumber++;
		BetterConsoleServer.userUsernameMap.put(user, username);
		BetterConsoleServer.broadcastMessage(sender = "Server", msg = (username + " joined the chat"));
	}

	@OnWebSocketClose
	public void onClose(Session user, int statusCode, String reason) {
		String username = BetterConsoleServer.userUsernameMap.get(user);
		BetterConsoleServer.userUsernameMap.remove(user);
		BetterConsoleServer.broadcastMessage(sender = "Server", msg = (username + " left the chat"));
	}

	@OnWebSocketMessage
	public void onMessage(Session user, String message) {
		BetterConsoleServer.broadcastMessage(sender = BetterConsoleServer.userUsernameMap.get(user), msg = message);
	}

}