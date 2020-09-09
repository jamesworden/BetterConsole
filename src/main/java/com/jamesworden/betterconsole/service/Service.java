package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import spark.Request;

import java.util.List;

/**
 * Middleman service to manipulate data from BetterConsole to the MC server
 *
 * @param <T> Model of data
 */
public interface Service<T> {

	List<T> findAll(Request req);

	T findById(Request req);

	T save(Request req, Gson gson);

}
