package com.jamesworden.betterconsole.models;

import org.inferred.freebuilder.FreeBuilder;

@FreeBuilder
public interface PluginModel {

	String getName();
	String getDescription();
	String getVersion();
	boolean isIgnored();
	boolean isEnabled();

	class Builder extends PluginModel_Builder{ }
}

