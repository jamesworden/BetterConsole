package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import org.bukkit.entity.Player;
import spark.Request;

import java.util.Collection;
import java.util.List;

public class PlayerService implements Service{

	public PlayerService(Collection<? extends Player> onlinePlayers) {

	}

	@Override
	public List findAll(Request req) {
		return null;
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
