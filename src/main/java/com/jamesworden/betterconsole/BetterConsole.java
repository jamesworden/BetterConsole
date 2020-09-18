package com.jamesworden.betterconsole;

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

	@Override
	public void onEnable() {

		// Load configuration
		saveDefaultConfig();
		Configuration configuration = Configuration.getInstance();
		configuration.init(getConfig());

		// Register in-game command
		Objects.requireNonNull(getCommand("betterconsole")).setExecutor(new GameCommand());

		// Start the server
		BetterConsoleServer.startServer();
	}

	@Override
	public void onDisable() {

		// Stop the server
		BetterConsoleServer.stopServer();
	}

}

// TODO - remove routes arraylist from betterconsoleserver
// TODO - Just declare the routes from the constructor - new route(...)
// TODO - Change services so all methods return lists
// TODO - Change routes so all of them handle model views properly
// TODO - Set up model and view system with each service / route
// TODO - Do simple html rendering, nothing complex, polish the front end after everything
// TODO - Delete the home service route, just make a basic get request
// TODO - Make a server stats route? TPS, CPU used? if possible.