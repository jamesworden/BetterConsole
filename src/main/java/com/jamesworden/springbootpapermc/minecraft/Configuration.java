package com.jamesworden.springbootpapermc.minecraft;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Values loaded from BetterConsole's config.yml
 */
public class Configuration {

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
		this.fileConfiguration = fileConfiguration;
		this.theme = fileConfiguration.getString("theme");
		this.port = fileConfiguration.getInt("port");
	}

	@Getter private FileConfiguration fileConfiguration;
	@Getter @Setter private String theme;
	@Getter private int port;

}
