package com.jamesworden.betterconsole.application;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Server {

	private ArrayList<Client> connectedClients;
	private RequestHandler requestHandler;
	private ServerSocket serverSocket;
	private final Logger LOGGER;
	private boolean running;
	private final int port;

	public Server(int port) {
		this.connectedClients = new ArrayList<>();
		this.requestHandler = new RequestHandler();
		this.LOGGER = Bukkit.getLogger();
		this.running = false;
		this.port = port;
	}

	/**
	 * Start the BetterConsole server
	 */
	public void start() {
		// Ensure the server is not running
		if (running) {
			LOGGER.severe("Unable to start server because it is already running!");
			return;
		}
		// Try to listen for client connections on specified port
		try {
			running = true;
			serverSocket = new ServerSocket(port);
			listen();

		} catch (Exception e) {
			LOGGER.info("Error trying to start the BetterConsole server!");
			e.printStackTrace();
		}

	}

	/**
	 * Stop the BetterConsole server
	 */
	public void stop() {

		// Ensure the server is running
		if (!running) {
			LOGGER.severe("Unable to stop the server because it is not running!");
			return;
		}

		// Try to stop the server
		try {
			LOGGER.info("BetterConsole server shutting down...");
			// TODO - Fix warning message when server socket closes?
			serverSocket.close();
			running = false;

		} catch (IOException e) {

			LOGGER.severe("There's been an error trying to close the server!");
			e.printStackTrace();
		}

	}

	/**
	 * Begin listening for client connections
	 * Will stop when the server is no longer running
	 */
	private void listen() {

		new Thread(() -> {

			while (running) {
				// Try to connect with a client
				try {

					// Wait for a client to connect
					System.out.println("Server Awaiting Client Connection...");
					Client client = new Client(serverSocket.accept());

					// Client connected
					connectedClients.add(client);
					new Thread(client).start();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// Clear all client connections from memory
			connectedClients.clear();

		}).start();

	}

	public ArrayList<Client> getConnectedClients() {
		return connectedClients;
	}

	public RequestHandler getRequestHandler() { return requestHandler; }
}
