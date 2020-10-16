package com.jamesworden.betterconsole.application;

import com.jamesworden.betterconsole.BetterConsole;
import org.bukkit.Bukkit;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class Client implements Runnable{

	private final Socket connection;
	protected final Logger LOGGER;

	public Client(Socket connection) {
		this.connection = connection;
		this.LOGGER = Bukkit.getLogger();
	}

	@Override
	public void run() {

		BufferedReader in;
		String fileRequested;
		OutputStream outStream;

		try {

			// Read socket input stream upon connecting
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			outStream = connection.getOutputStream();

			// Filter through the connection request for type and file
			String firstLine = in.readLine();

			// Ensure that the connection request has content at all
			if (firstLine == null) {
				LOGGER.warning("There was an error reading the input stream!");
				return;
			}

			// Establish type of HTTP request & determine file to be returned
			StringTokenizer stringTokenizer = new StringTokenizer(firstLine);
			String method = stringTokenizer.nextToken().toUpperCase();
			fileRequested = stringTokenizer.nextToken().toLowerCase();

			// Handle the request
			BetterConsole.getInstance().getWebServer().getRequestHandler()
					.handle(method, fileRequested, connection, outStream);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}