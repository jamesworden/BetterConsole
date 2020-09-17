package com.jamesworden.betterconsole.minecraft;

import com.jamesworden.betterconsole.util.DesktopApi;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.URI;

/**
 * GameCommand to open BetterConsole from the original console
 */
public class GameCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

		// Ensure command sender is console
		if (sender instanceof Player) {
			int port = Configuration.getInstance().getPort();
			sender.sendMessage(ChatColor.YELLOW + " BetterConsole enabled on port " + port +" !");
			
			return false;
		}

		// Attempt to open browser
		int port = Configuration.getInstance().getPort();
		URI uri = DesktopApi.getURI(port);

		DesktopApi.browse(uri);
		return false;
	}
}
