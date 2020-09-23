package com.jamesworden.betterconsole.application;

import com.jamesworden.betterconsole.BetterConsole;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class Client implements Runnable{

	private final Socket connection;

	public Client(Socket connection) {
		this.connection = connection;
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
			StringTokenizer stringTokenizer = new StringTokenizer(firstLine);

			// Establish type of HTTP request & determine file to be returned
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