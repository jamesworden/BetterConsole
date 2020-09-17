package com.jamesworden.betterconsole;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.minecraft.Configuration;
import com.jamesworden.betterconsole.routes.Route;
import com.jamesworden.betterconsole.service.PlayerService;
import com.jamesworden.betterconsole.service.PluginService;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.logging.Logger;

import static spark.Spark.*;
import static spark.Spark.port;

public class BetterConsoleServer {

	private static ArrayList<Route> routes = new ArrayList<>();
	private static Logger LOGGER = Bukkit.getLogger();
	private static boolean running = false;

	/**
	 * Start the BetterConsole server
	 */
	static void startServer() {

		// Error handler for failed server initialization
		initExceptionHandler(e -> LOGGER.severe("Unable to start server on port " + "port" + "!"));

		// Configuration
		staticFiles.location("/public");

		configurePort();
		configureRoutes();

		running = true;
	}

	/**
	 * Stop the BetterConsole server
	 */
	static void stopServer() {
		LOGGER.info("BetterConsole server shutting down...");
		running = false;
		stop();
	}

	/**
	 * Configuring the BetterConsole server port
	 */
	private static void configurePort() {
		Configuration configuration = Configuration.getInstance();
		int port = configuration.getPort();

		LOGGER.info("BetterConsole server starting on port " + port + "...");
		port(port);
	}

	/**
	 * Configuring the BetterConsole server routes
	 */
	private static void configureRoutes() {
		routes.add(new Route("/plugins", new PluginService()));
		routes.add(new Route("/players", new PlayerService()));

		Gson gson = new Gson();
		for (Route route : routes) {
			route.Configure(gson);
		}
	}
}
