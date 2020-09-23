package com.jamesworden.betterconsole.util;

import org.bukkit.Bukkit;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public class DesktopApi {

	/**
	 * Attempts to open user's desktop browser on the specified URI
	 *
	 * @param uri URI to browse desktop with
	 */
	public static void browse(URI uri) {

		try {

			if (uri == null) throw new Exception();

			String osType = System.getProperty("os.name").toLowerCase();

			// Windows
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				Desktop.getDesktop().browse(uri);
				return;
			}

			Runtime runtime = Runtime.getRuntime();

			// Mac
			if (osType.contains("mac")) {
				runtime.exec("open " + uri);
			}

			// Linux
			else if ( (osType.contains("nix")) || (osType.contains("nux")) ) {
				runtime.exec("xdg-open" + uri);
			}

			throw new Exception();

		} catch (Exception ignored) {
			Bukkit.getLogger().warning("BetterConsole available on: " + uri);
		}

	}

	/**
	 * Get localhost URI
	 *
	 * @param port
	 * @return Localhost's URI on specified port
	 */
	public static URI getURI(int port) {
		try {
			return new URI("http://localhost:" + port + "/betterconsole");
		} catch (URISyntaxException ignored) {
			return null;
		}
	}


}
