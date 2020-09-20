package com.jamesworden.betterconsole.application;

import com.jamesworden.betterconsole.minecraft.Configuration;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

import org.glassfish.tyrus.server.Server;

public class BCServer {

	private static boolean running = false;
	private static Server server;
	private static Logger LOGGER;
	private static int port;

	/**
	 * Start the BetterConsole server
	 */
	public static void startServer() {

		if (running) {
			LOGGER.severe("Unable to start server because it is already running!");
			return;
		}

		port = Configuration.getInstance().getPort();
		LOGGER = Bukkit.getLogger();
		running = false;

		try {
			server = new Server("localhost", port, "/", null, BCServerEndpoint.class);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("BetterConsole server started on port " + port + "!");
		running = true;
	}

	/**
	 * Stop the BetterConsole server
	 */
	public static void stopServer() {

		if (!running) {
			LOGGER.severe("Unable to stop the server because it is not running!");
			return;
		}

		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("BetterConsole server shutting down...");
		running = false;
	}

}
