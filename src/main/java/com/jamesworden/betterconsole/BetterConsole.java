package com.jamesworden.betterconsole;

import com.jamesworden.betterconsole.minecraft.Configuration;
import com.jamesworden.betterconsole.minecraft.GameCommand;
import org.bukkit.Bukkit;
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

		// Register command
		Objects.requireNonNull(getCommand("betterconsole")).setExecutor(new GameCommand());

		// Start the server
		BetterConsoleServer.startServer();

		// TODO - Set up model and view system with each service / route
		// TODO - Do simple html rendering, nothing complex, polish the front end after everything
		// TODO - Delete the home service route, just make a basic get request
		// TODO - Make a server stats route? TPS, CPU used? if possible.

	}

	@Override
	public void onDisable() {

		// Stop the server
		BetterConsoleServer.stopServer();
	}

}
