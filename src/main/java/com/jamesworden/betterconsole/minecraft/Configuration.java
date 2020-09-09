package com.jamesworden.betterconsole.minecraft;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Values loaded from BetterConsole's config.yml
 */
public class Configuration {

	private String theme;
	private int port;

	// Singleton pattern
	private static Configuration instance = null;

	private Configuration() {}

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	/**
	 * Initializes plugin configuration
	 *
	 * @param fileConfiguration Configuration taken from the plugin's config.yml file
	 */
	public void init(FileConfiguration fileConfiguration) {
		theme = fileConfiguration.getString("theme");
		port = fileConfiguration.getInt("port");
	}

	public int getPort() {
		return port;
	}

	public String getTheme() {
		return theme;
	}


}
