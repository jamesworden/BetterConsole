package com.jamesworden.betterconsole.models;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class PluginModel {

	public abstract String getName();
	public abstract boolean isIgnored();
	public abstract boolean isEnabled();

	public static Builder builder() {
		return new AutoValue_PluginModel.Builder();
	}


	@AutoValue.Builder
	public abstract static class Builder {
		public abstract Builder setName(String newName);

		public abstract Builder setIgnored(boolean newIgnored);

		public abstract Builder setEnabled(boolean newEnabled);

		public abstract PluginModel build();
	}
}
