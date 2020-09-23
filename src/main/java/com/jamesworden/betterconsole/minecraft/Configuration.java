package com.jamesworden.betterconsole.minecraft;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Values loaded from BetterConsole's config.yml
 */
public class Configuration {

	private String theme;
	private int port;

	/**
	 * Initializes plugin configuration
	 *
	 * @param fileConfiguration Configuration taken from the plugin's config.yml file
	 */
	public Configuration(FileConfiguration fileConfiguration) {
		theme = fileConfiguration.getString("theme");
		port = fileConfiguration.getInt("port");
	}

	public int getPort() { return port; }
	public String getTheme() {
		return theme;
	}


}
