package com.jamesworden.springbootpapermc;

import com.jamesworden.springbootpapermc.minecraft.Configuration;
import com.jamesworden.springbootpapermc.minecraft.GameCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;
import java.util.Collections;
import java.util.Objects;

/**
 * Main plugin class for BetterConsole
 *
 * @author James Worden
 */
public final class Driver extends JavaPlugin {

	private ConfigurableApplicationContext context;
	private Configuration configuration;

	@Override
	public void onEnable() {

		// Load configuration
		saveDefaultConfig();
		configuration = Configuration.getInstance();
		configuration.init(getConfig());

		// Register command
		Objects.requireNonNull(getCommand("betterconsole")).setExecutor(new GameCommand());

		// Configure server port and properties
		SpringApplication app = new SpringApplicationBuilder(Application.class)
			.properties("server.port=" + configuration.getPort())
			.bannerMode(Banner.Mode.OFF)
			.logStartupInfo(false)
			.headless(false)
			.application();

		// Start the server
		context = app.run();
		context.start();
	}

	@Override
	public void onDisable() {

		// Stop the server
		if (context != null) context.close();
	}

}
