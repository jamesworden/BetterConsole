package com.jamesworden.betterconsole;

import com.jamesworden.betterconsole.application.Server;
import com.jamesworden.betterconsole.minecraft.Configuration;
import com.jamesworden.betterconsole.minecraft.GameCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * Main plugin class for BetterConsole
 *
 * @author James Worden
 */
public final class BetterConsole extends JavaPlugin {

	private static Server server;
	private static BetterConsole instance;
	private static Configuration configuration;

	@Override
	public void onEnable() {
		// Configuration
		saveDefaultConfig();
		configuration = new Configuration(getConfig());

		// Register in-game command
		Objects.requireNonNull(getCommand("betterconsole")).setExecutor(new GameCommand());

		// Start the server
		server = new Server(configuration.getPort());
		server.start();

		instance = this;
	}

	@Override
	public void onDisable() {
		// Stop the server
		server.stop();
	}

	public static BetterConsole getInstance() { return instance; }
	public Configuration getConfiguration() { return configuration; }
	public Server getWebServer() { return server; }
}


// TODO - Open an HTML page and use JS to fetch WS data?

// TODO - Do simple html rendering, nothing complex, polish the front end after everything
// TODO - Delete the home service route, just make a basic get request
// TODO - Make a server stats route? TPS, CPU used? if possible.