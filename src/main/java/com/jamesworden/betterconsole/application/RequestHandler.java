package com.jamesworden.betterconsole.application;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

public class RequestHandler {

	private final Logger LOGGER = Bukkit.getLogger();

	/**
	 * Handles HTTP requests
	 *
	 * @param method     HTTP request method type
	 * @param route      Specified route
	 * @param connection The socket connection that requests this information
	 * @param outStream  The output stream of the socket.
	 */
	public void handle(String method, String route, Socket connection, OutputStream outStream) {

		// Declare HTTP request variables
		BufferedOutputStream dataOut = new BufferedOutputStream(outStream);
		PrintWriter out = new PrintWriter(outStream);
		byte[] fileData = getBytes(route);
		String statusMessage;
		int statusCode;

		// Ensure that GET requests are the only ones allowed
		if (method.equals("GET")) {
			statusMessage = "OK!";
			statusCode = 200;
		}  else {
			statusMessage = "Not Implemented";
			statusCode = 501;
		}

		// Ensure that file bytes were returned
		if (fileData == null) {
			LOGGER.warning("There's been an error getting bytes from files!");
			return;
		}

		int fileLength = fileData.length;

		out.println("HTTP/1.1 " + statusCode + " " + statusMessage);
		out.println("Server: BetterConsole : 1.0"); // TODO - Fetch plugin name and version from Bukkit
		out.println("Date: " + new Date());
		out.println("Content-type: " + "text/html"); // TODO - have other content types?
		out.println("Content-length: " + fileLength);

		out.println();
		out.flush();

		try {
			dataOut.write(fileData, 0, fileLength);
			dataOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get bytes of appropriate file from specified route name
	 *
	 * @param routeName name of route
	 * @return byte array composing the file related to the route name
	 * @apiNote To get the byte array the specified file must be in the 'resources' folder.
	 */
	private byte[] getBytes(String routeName) {

		String fileName;

		// Determine what file to be returned from route
		if (routeName.equalsIgnoreCase("/betterconsole")) {
			fileName = "index.html";
		} else {
			fileName = "file_not_found.html";
		}

		// Return the array of bytes from the correct file
		try {
			return IOUtils.toByteArray(Objects.requireNonNull(this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(fileName)));

		} catch (Exception e) {
			LOGGER.warning("There's been an error getting file data from file with name: " + fileName + "!");
			e.printStackTrace();
		}

		// Return null if unable to get bytes
		return null;
	}

}