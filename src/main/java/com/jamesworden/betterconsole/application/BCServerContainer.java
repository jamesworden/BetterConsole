package com.jamesworden.betterconsole.application;

import com.jamesworden.betterconsole.minecraft.Configuration;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class BCServerContainer {

	private static boolean running = false;
	private static BCServer server;
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
			server = new BCServer(port);
			server.start();

		} catch (Exception e) {
			LOGGER.info("Error trying to start the BetterConsole server!");
			e.printStackTrace();

			return;
		}

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
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("BetterConsole server shutting down...");
		running = false;
	}

}
