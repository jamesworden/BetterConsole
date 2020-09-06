package com.jamesworden.springbootpapermc;

import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public final class Driver extends JavaPlugin {

	private ConfigurableApplicationContext context;

	@Override
	public void onLoad() {
		context = SpringApplication.run(Application.class);
	}

	@Override
	public void onEnable() {
		// Plugin startup logic
		context.start();
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		context.close();
	}
}
