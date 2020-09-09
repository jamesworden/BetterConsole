package com.jamesworden.springbootpapermc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {
	private boolean isRunning;

	public static void main(String[] args) {}

	public void run(ApplicationArguments args) {
		System.out.println("Hello World");
	}

	public void start() {
		System.out.println("Start");
		isRunning = true;
	}

	public void stop() {
		System.out.println("Stop");
		isRunning = false;
	}

	public boolean isRunning() {
		return isRunning;
	}
}
