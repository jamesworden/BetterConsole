package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.domain.PluginModel;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class PluginService implements Service<PluginModel> {

	/**
	 * Returns all plugins and ignored plugins on the minecraft server
	 *
	 * @return all active and ignored plugin domain
	 */
	@Override
	public List<PluginModel> findAll() {
		return getCurrentPluginModels();
	}

	/**
	 * Returns an active or ignored plugin model from plugin on the server
	 *
	 * @param id request that contains the name of the plugin to be returned
	 * @return respective plugin model
	 */
	@Override
	public PluginModel findById(String id){

		for (PluginModel model : getCurrentPluginModels()) {
			if (model.getName().equalsIgnoreCase(id)) {
				return model;
			}
		}
		return null;
	}

	/**
	 * TODO - Change route to default plugin page
	 */
	@Override
	public List<PluginModel> save(String request, Gson gson) {
		return null;
	}

	/**
	 * Gets plugin data from minecraft server
	 *
	 * @return Hash map of plugin names and domain, respectively
	 */
	private List<PluginModel> getCurrentPluginModels() {
		List<PluginModel> pluginModels = new ArrayList<>();

		Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
		List<String> ignoredPluginNames = Bukkit.getHelpMap().getIgnoredPlugins();

		for (Plugin plugin : plugins) {
			pluginModels.add(getPluginModel(plugin));
		}

		for (String ignoredPluginName : ignoredPluginNames) {
			pluginModels.add(getIgnoredPluginModel(ignoredPluginName));
		}

		return pluginModels;
	}

	/**
	 * Creates plugin model for active plugin
	 *
	 * @param plugin plugin to make a model of
	 * @return model of specified plugin
	 */
	private PluginModel getPluginModel(Plugin plugin) {
		return new PluginModel.Builder()
				.setName(plugin.getName())
				.setVersion(plugin.getDescription().getVersion())
				.setDescription(plugin.getDescription().getDescription())
				.setEnabled(plugin.isEnabled())
				.setIgnored(false)
				.build();
	}


	/**
	 * Creates plugin model for ignored plugin
	 *
	 * @param pluginName ignored plugin name to make a model of
	 * @return ignored model of specified plugin name
	 */
	private PluginModel getIgnoredPluginModel(String pluginName) {
		return new PluginModel.Builder()
				.setName(pluginName)
				.setEnabled(false)
				.setIgnored(true)
				.build();
	}

}
