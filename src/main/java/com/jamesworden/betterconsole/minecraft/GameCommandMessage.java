package com.jamesworden.betterconsole.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Holds in-game chat functionality
 */
public class GameCommandMessage {

	/**
	 * Informs target that players cannot use command
	 *
	 * @param target
	 */
	public void playerErrorMessage(CommandSender target) {
		target.sendMessage(ChatColor.RED + " Players may not use this command!");
	}

}
