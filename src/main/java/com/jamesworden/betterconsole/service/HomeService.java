package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import spark.Request;

import java.util.HashMap;

public class HomeService implements Service{

	/**
	 * Home page GET request service. This should be the only working service method for the Home page.
	 *
	 * @return
	 */
	@Override
	public HashMap<String, Object> findAll() {
		HashMap<String, Object> test = new HashMap<>();
		test.put("example-text", "hello!");
		test.put("example-text", "testing text!");
		return test;
	}

	@Override
	public HashMap<String, Object> findById(Request req) {
		return null;
	}

	@Override
	public HashMap<String, Object> save(Request req, Gson gson) {
		return null;
	}
}
