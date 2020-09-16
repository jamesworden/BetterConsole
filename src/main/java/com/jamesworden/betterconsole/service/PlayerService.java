package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.models.PlayerModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import spark.Request;

import java.util.Collection;
import java.util.HashMap;

public class PlayerService implements Service<PlayerModel>{

	@Override
	public HashMap<String, PlayerModel> findAll() {
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		HashMap<String, PlayerModel> playerModels = new HashMap<>();

		for (Player player : onlinePlayers) {
//			String name = player.getName();
//			PlayerModel model = PlayerModel.
//			playerModels.put(name, model);
		}

		return playerModels;
	}

	@Override
	public HashMap findById(Request req) {
		return null;
	}

	@Override
	public HashMap save(Request req, Gson gson) {
		return null;
	}
}
