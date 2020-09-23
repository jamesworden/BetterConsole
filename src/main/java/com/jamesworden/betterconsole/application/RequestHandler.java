package com.jamesworden.betterconsole.application;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

public class RequestHandler {

	private final byte[] INDEX, FILE_NOT_FOUND, METHOD_NOT_SUPPORTED;
	private final Logger LOGGER;

	public RequestHandler() {
		INDEX = getBytesFromResourceFile("index.html");
		FILE_NOT_FOUND = getBytesFromResourceFile("file_not_found.html");
		METHOD_NOT_SUPPORTED = getBytesFromResourceFile("method_not_supported.html");
		LOGGER = Bukkit.getLogger();
	}

	private byte[] getBytesFromResourceFile(String fileName) {
		try {
			return IOUtils.toByteArray(Objects.requireNonNull(this
					.getClass().getClassLoader().getResourceAsStream(fileName)));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Handles HTTP requests
	 *
	 * @param method HTTP request method type
	 * @param fileName Specified route
	 * @param connection The socket connection that requests this information
	 * @param outStream The output stream of the socket.
	 */
	public void handle (String method, String fileName, Socket connection, OutputStream outStream) {

		BufferedOutputStream dataOut = new BufferedOutputStream(outStream);
		PrintWriter out = new PrintWriter(outStream);
		InputStream fileInputStream;
		String statusMessage;
		int statusCode;
		byte[] fileData;

		if (method.equals("GET")) {
			fileData = getBytesFromFileName(fileName);
			statusMessage = "OK!";
			statusCode = 200;
		} else {
			fileData = METHOD_NOT_SUPPORTED;
			statusMessage = "Not Implemented";
			statusCode = 501;
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
	 * Returns the array of bytes that compose a given file's input stream
	 *
	 * @param fileInputStream Input stream of file
	 * @param fileLength Length of the specified input stream
	 * @return Array of bytes from specified input stream
	 */
	private byte[] getFileData(InputStream fileInputStream, int fileLength) {

		// Define array
		byte[] fileData = new byte[fileLength];

		// Try to read stream to data array
		try {
			fileInputStream.read(fileData);

		} catch (Exception e) {
			LOGGER.severe("Error trying to read file data: ");
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOGGER.severe("Error while closing stream: ");
					e.printStackTrace();
				}
			}
		}
		return fileData;
	}

	/**
	 * Returns the corresponding file from the file name input
	 *
	 * Currently, the single meaningful file to be returned is 'index.html'
	 * It will be returned through 'localhost:port/betterconsole'
	 *
	 * @param fileName name of file input stream to be returned
	 * @return input stream from specified file name, null if name not found
	 */
	private byte[] getBytesFromFileName(String fileName) {

		if (fileName.equals("/betterconsole")) {
			return INDEX;
		} else {
			return FILE_NOT_FOUND;
		}
	}

}