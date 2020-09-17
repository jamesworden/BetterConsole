package com.jamesworden.betterconsole;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.minecraft.Configuration;
import com.jamesworden.betterconsole.routes.Route;
import com.jamesworden.betterconsole.service.PlayerService;
import com.jamesworden.betterconsole.service.PluginService;
import org.bukkit.Bukkit;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import static j2html.TagCreator.*;
import static spark.Spark.*;
import static spark.Spark.port;

public class BetterConsoleServer {

	private static ArrayList<Session> sessions = new ArrayList<>();
	private static Logger LOGGER = Bukkit.getLogger();
	private static boolean running = false;

	static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
	static int nextUserNumber = 1; //Assign to username for next connecting user

	/**
	 * Start the BetterConsole server
	 */
	static void startServer() {

//		// Error handler for failed server initialization
//		initExceptionHandler(e -> LOGGER.severe("Unable to start server on port " + "port" + "!"));
//
//		staticFiles.location("/public");
//		staticFiles.expireTime(600);
//
//		Configuration configuration = Configuration.getInstance();
//		int port = configuration.getPort();
//		LOGGER.info("BetterConsole server starting on port " + port + "...");
//		port(port);
//
//		webSocket("/", BetterConsoleWebSocket.class);
//
//		running = true;
//		init();

		staticFiles.location("/public"); //index.html is served at localhost:4567 (default port)
		staticFiles.expireTime(600);
		webSocket("/chat", ChatWebSocketHandler.class);
		webSocket("/", ChatWebSocketHandler.class);
		init();
	}

	/**
	 * Stop the BetterConsole server
	 */
	static void stopServer() {
		LOGGER.info("BetterConsole server shutting down...");

		running = false;
		stop();
	}

	public static void addSession(Session session) {
		sessions.add(session);
	}

	public static void removeSession(Session session) {
		sessions.remove(session);
	}

	//Sends a message from one user to all users, along with a list of current usernames
	public static void broadcastMessage(String sender, String message) {
		userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
			try {
				session.getRemote().sendString(String.valueOf(new JSONObject()
						.put("userMessage", createHtmlMessageFromSender(sender, message))
						.put("userlist", userUsernameMap.values())
				));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	//Builds a HTML element with a sender-name, a message, and a timestamp,
	private static String createHtmlMessageFromSender(String sender, String message) {
		return article(
				b(sender + " says:"),
				span(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date())),
				p(message)
		).render();
	}

}
