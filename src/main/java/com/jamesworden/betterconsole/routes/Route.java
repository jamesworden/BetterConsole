package com.jamesworden.betterconsole.routes;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.service.Service;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

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
		path(path, () -> {
			get("", (req, res) -> {
				Map<String, Object> model = service.findAll();
				return new VelocityTemplateEngine().render(
					new ModelAndView(model,"view-name")
				);
			});

			get("/:id", (req, res) -> {
				Map<String, Object> model = service.findById(req);
				return new VelocityTemplateEngine().render(
						new ModelAndView(model,"view-name")
				);
			});

			post("", (req, res) -> {
				Map<String, Object> model = service.save(req, gson);
				return new VelocityTemplateEngine().render(
						new ModelAndView(model,"view-name")
				);
			});

		});

	}

}
