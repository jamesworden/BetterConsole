package com.jamesworden.betterconsole.routes;

import com.jamesworden.betterconsole.service.Service;
import com.jamesworden.betterconsole.service.PluginService;

import static spark.Spark.*;

public class PluginRoute implements Route {

	private static Service service;

	@Override
	public void Configure() {

		// Assign service
		service = new PluginService();

		// Set Path
		path("/", () -> service.findAll());
	}
}
