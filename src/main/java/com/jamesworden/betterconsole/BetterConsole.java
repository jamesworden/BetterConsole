package com.jamesworden.betterconsole;

import com.jamesworden.betterconsole.application.BCServer;
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

		// Configuration
		saveDefaultConfig();
		Configuration.getInstance().init(getConfig());

		// Start the server
		BCServer.startServer();

		// Register in-game command
		Objects.requireNonNull(getCommand("betterconsole")).setExecutor(new GameCommand());
	}

	@Override
	public void onDisable() {

		// Stop the server
		BCServer.stopServer();
	}

}

// TODO - Do simple html rendering, nothing complex, polish the front end after everything
// TODO - Delete the home service route, just make a basic get request
// TODO - Make a server stats route? TPS, CPU used? if possible.