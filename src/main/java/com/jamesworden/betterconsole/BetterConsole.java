package com.jamesworden.betterconsole;

import com.jamesworden.betterconsole.minecraft.Configuration;
import com.jamesworden.betterconsole.minecraft.GameCommand;
import com.jamesworden.betterconsole.routes.Route;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

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
	}

	@Override
	public void onDisable() {

		// Stop the server
		BetterConsoleServer.stopServer();
	}

}
