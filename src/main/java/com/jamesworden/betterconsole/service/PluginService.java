package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.models.PluginModel;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import spark.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PluginService implements Service<PluginModel> {

	/**
	 * Returns all plugins and ignored plugins on the minecraft server
	 *
	 * @return all active and ignored plugin models
	 */
	@Override
	public HashMap<String, PluginModel> findAll() {
		return getCurrentPluginModels();
	}

	/**
	 * Returns an active or ignored plugin model from plugin on the server
	 *
	 * @param req request that contains the name of the plugin to be returned
	 * @return respective plugin model
	 */
	@Override
	public HashMap<String, PluginModel> findById(Request req){
		HashMap<String, PluginModel> pluginModels = getCurrentPluginModels();

		Set<String> keys = pluginModels.keySet();
		String id = req.body();

		for (String key : keys) {
			if (id.equalsIgnoreCase(key)) {
				HashMap<String, PluginModel> model = new HashMap<>();
				model.put(key,pluginModels.get(key));
				return model;
			}
		}

		return null;
	}

	/**
	 * TODO - Change route to default plugin page
	 *
	 * @param req
	 * @param gson
	 * @return
	 */
	@Override
	public HashMap<String, PluginModel> save(Request req, Gson gson) {
		return null;
	}

	/**
	 * Gets plugin data from minecraft server
	 *
	 * @return Hash map of plugin names and models, respectively
	 */
	private HashMap<String, PluginModel> getCurrentPluginModels() {
		HashMap<String, PluginModel> pluginModels = new HashMap<>();

		Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
		List<String> ignoredPluginNames = Bukkit.getHelpMap().getIgnoredPlugins();

		for (Plugin plugin : plugins) {
			PluginModel model = getPluginModel(plugin);
			pluginModels.put("plugin", model);
		}

		for (String ignoredPluginName : ignoredPluginNames) {
			PluginModel model = getIgnoredPluginModel(ignoredPluginName);
			pluginModels.put("ignored-plugin", model);
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
