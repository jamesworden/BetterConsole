package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.models.PluginModel;
import org.bukkit.plugin.Plugin;
import spark.Request;

import java.util.List;

public class PluginService implements Service {

	public List<PluginModel> pluginModels;

	public PluginService(Plugin[] plugins, List<String> ignoredPlugins) {
		for (Plugin plugin : plugins) {

		}
	}

	@Override
	public List<PluginModel> findAll(Request req) {
		return pluginModels;
	}

	@Override
	public Object findById(Request req) {
		return null;
	}

	@Override
	public Object save(Request req, Gson gson) {
		return null;
	}
}
