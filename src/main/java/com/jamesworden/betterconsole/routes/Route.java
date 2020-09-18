package com.jamesworden.betterconsole.routes;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.service.Service;

/**
 * Generic HTTP Request Route. Handles GET and POST methods only.
 */
public class Route {

	/** HTTP path */
	private String path;

	/** Corresponding service to fetch server data */
	private Service service;

	public Route(String path, Service service) {
		this.path = path;
		this.service = service;
	}

	/**
	 * Configures the routes for the BetterConsole web server
	 */
	public void Configure(Gson gson) {

//		// Basic example path: '/plugins'
//		path(path, () -> {
//			get("", (req, res) -> new VelocityTemplateEngine().render(
//					// Path: 'plugins'
//					new ModelAndView(service.findAll(),path.substring(1) + "")
//			));
//
//			get("/:id", (req, res) -> new VelocityTemplateEngine().render(
//					// Path: 'plugin'
//					new ModelAndView(service.findById(req), path.substring(1, path.length()-1))
//			));
//
//			post("", (req, res) -> new VelocityTemplateEngine().render(
//					// Path: 'plugins-post'
//					new ModelAndView(service.save(req, gson),path.substring(1) + "-post")
//			));
//
//		});

	}

}
