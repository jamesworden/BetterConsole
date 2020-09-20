package com.jamesworden.betterconsole.domain;

import org.inferred.freebuilder.FreeBuilder;

import java.net.InetSocketAddress;
import java.util.UUID;

@FreeBuilder
public interface PlayerModel {

	String getName();
	UUID getUniqueId();
	InetSocketAddress getAddress();
	String getGameMode();
	boolean isOp();

	class Builder extends PlayerModel_Builder { }
}


