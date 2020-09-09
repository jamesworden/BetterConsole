package com.jamesworden.betterconsole.service;

import com.jamesworden.betterconsole.models.PluginModel;

import java.util.List;

public class PluginService implements Service {

	public List<PluginModel> pluginModels;

	@Override
	public List<PluginModel> findAll() {
		return pluginModels;
	}
}
