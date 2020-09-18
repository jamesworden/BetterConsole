package com.jamesworden.betterconsole;

import com.jamesworden.betterconsole.minecraft.Configuration;
import org.bukkit.Bukkit;
import java.util.logging.Logger;

public class BetterConsoleServer {

	private static Logger LOGGER = Bukkit.getLogger();
	private static boolean running = false;
	// private static Server server;

	/**
	 * Start the BetterConsole server
	 */
	static void startServer() {

		int port = Configuration.getInstance().getPort();
		// server = new Server("localhost", port , "/", BetterConsoleServerEndpoint.class);

		try {
			// server.start();
			running = true;
			LOGGER.info("BetterConsole server started on port " + port + "!");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Stop the BetterConsole server
	 */
	static void stopServer() {

		try {
			// server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("BetterConsole server shutting down...");
		running = false;
	}

}
