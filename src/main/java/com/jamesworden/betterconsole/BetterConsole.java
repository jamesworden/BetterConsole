package com.jamesworden.betterconsole;

import com.jamesworden.betterconsole.minecraft.Configuration;
import com.jamesworden.betterconsole.minecraft.GameCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.*;

/**
 * Main plugin class for BetterConsole
 *
 * @author James Worden
 */
public final class BetterConsole extends JavaPlugin {

	private Configuration configuration;
	private Logger LOGGER = Bukkit.getLogger();
	private boolean running = false;

	@Override
	public void onEnable() {

		// Load configuration
		saveDefaultConfig();
		configuration = Configuration.getInstance();
		configuration.init(getConfig());

		// Register command
		Objects.requireNonNull(getCommand("betterconsole")).setExecutor(new GameCommand());

		// Start the server
		startServer();
	}

	@Override
	public void onDisable() {

		// Stop the server
		stopServer();
	}

	/**
	 * Start the BetterConsole server
	 */
	private void startServer() {

		// Error handler for failed server initialization
		initExceptionHandler(e -> LOGGER.severe("Unable to start server on port " + "port" + "!"));

		// Configuration
		int port = configuration.getPort();
		port(port);

		LOGGER.info("BetterConsole server starting on port " + port + "...");
		get("/",(req, res) -> "hello");
	}

	/**
	 * Stop the BetterConsole server
	 */
	private void stopServer() {
		LOGGER.info("BetterConsole server shutting down...");
		stop();
	}

}
