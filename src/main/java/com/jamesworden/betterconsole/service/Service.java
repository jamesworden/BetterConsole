package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import spark.Request;

import java.util.List;

/**
 * Retrieves data from minecraft server to BetterConsole server
 *
 * @param <T> Model of data
 */
public interface Service<T> {

	List<T> findAll();

	T findById(Request req);

	List<T> save(Request req, Gson gson);

}
