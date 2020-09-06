package com.jamesworden.springbootpapermc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.SmartLifecycle;

public class Application implements ApplicationRunner, SmartLifecycle {
	private boolean isRunning;

	@Override
	public void run(ApplicationArguments args) {
		System.out.println("Hello World");
	}

	@Override
	public void start() {
		System.out.println("Start");
		isRunning = true;
	}

	@Override
	public void stop() {
		System.out.println("Stop");
		isRunning = false;
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}
}
