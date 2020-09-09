package com.jamesworden.betterconsole.models;

public class PluginModel {

	private String name;
	private boolean enabled;

	public PluginModel(String name, boolean enabled) {
		this.name = name;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled() {
		enabled = false;
	}

}
