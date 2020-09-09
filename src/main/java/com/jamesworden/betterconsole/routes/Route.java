package com.jamesworden.betterconsole.routes;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.service.Service;

import static spark.Spark.*;

public class Route {

	private String path;
	private Service service;

	public Route(String path, Service service) {
		this.path = path;
		this.service = service;
	}

	public void Configure(Gson gson) {
		path(path, () -> {
			get("", (req, res) -> service.findAll(req), gson::toJson);
			get("/:id",(req, res) -> service.findById(req), gson::toJson);
			post("",(req, res) -> service.save(req, gson), gson::toJson);
			// put("",(req, res) -> service.update(req, gson), gson::toJson);
			// delete("",(req, res) -> service.delete(req), gson::toJson);
		});
	}

}
