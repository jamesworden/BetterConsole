package com.jamesworden.betterconsole.application;

import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

public class Client implements Runnable{

	private Socket connection;
	private Logger LOGGER;


	private static final String OUTPUT = "<html><head><title>Testing</title></head><body><p>Hello World</p></body></html>";
	private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
			"Content-Type: text/html\r\n" +
			"Content-Length: ";
	private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";

	public Client(Socket connection) {

		this.connection = connection;
		this.LOGGER = Bukkit.getLogger();

		LOGGER.info("Established new connection with address: " + connection.getInetAddress());
	}

	@Override
	public void run() {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			System.out.println("Incoming Data...");
			String line = reader.readLine();
			while(!line.isEmpty()) {
				System.out.println(line);
				line = reader.readLine();
				if(line.isEmpty()) {
					break;
				}
			}
			String response = OUTPUT_HEADERS + OUTPUT.length() + OUTPUT_END_OF_HEADERS + OUTPUT;
			connection.getOutputStream().write(response.getBytes("UTF-8"));
			connection.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
