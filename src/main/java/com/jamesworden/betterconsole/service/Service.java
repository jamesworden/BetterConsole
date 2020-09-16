package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import spark.Request;

import java.util.HashMap;

/**
 * Retrieves data from minecraft server to BetterConsole server
 *
 * @param <T> Model of data
 */
public interface Service<T> {

	HashMap<String, T> findAll();

	HashMap<String, T> findById(Request req);

	HashMap<String, T> save(Request req, Gson gson);

}
