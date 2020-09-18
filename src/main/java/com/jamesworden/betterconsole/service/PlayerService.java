package com.jamesworden.betterconsole.service;

import com.google.gson.Gson;
import com.jamesworden.betterconsole.models.PlayerModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerService implements Service<PlayerModel>{

	@Override
	public List<PlayerModel> findAll() {
		ArrayList<PlayerModel> playerModels = new ArrayList<>();

		for (Player player : Bukkit.getOnlinePlayers()) {
			playerModels.add(getPlayerModel(player));
		}
		return playerModels;
	}

	@Override
	public PlayerModel findById(String id) {

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getName().equalsIgnoreCase(id)) {
				return getPlayerModel(player);
			}
		}
		return null;
	}

	@Override
	public List save(String request, Gson gson) {
		return null;
	}

	private PlayerModel getPlayerModel(Player player) {
		return new PlayerModel.Builder()
				.setName(player.getName())
				.setAddress(player.getAddress())
				.setUniqueId(player.getUniqueId())
				.setGameMode(player.getGameMode().toString())
				.setOp(player.isOp())
				.build();
	}
}
