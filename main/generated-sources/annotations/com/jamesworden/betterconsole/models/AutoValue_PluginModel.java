package com.jamesworden.betterconsole.models;

import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_PluginModel extends PluginModel {

  private final String name;

  private final boolean ignored;

  private final boolean enabled;

  private AutoValue_PluginModel(
      String name,
      boolean ignored,
      boolean enabled) {
    this.name = name;
    this.ignored = ignored;
    this.enabled = enabled;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isIgnored() {
    return ignored;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public String toString() {
    return "PluginModel{"
        + "name=" + name + ", "
        + "ignored=" + ignored + ", "
        + "enabled=" + enabled
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PluginModel) {
      PluginModel that = (PluginModel) o;
      return this.name.equals(that.getName())
          && this.ignored == that.isIgnored()
          && this.enabled == that.isEnabled();
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= name.hashCode();
    h$ *= 1000003;
    h$ ^= ignored ? 1231 : 1237;
    h$ *= 1000003;
    h$ ^= enabled ? 1231 : 1237;
    return h$;
  }

  static final class Builder extends PluginModel.Builder {
    private String name;
    private Boolean ignored;
    private Boolean enabled;
    Builder() {
    }
    @Override
    public PluginModel.Builder setName(String name) {
      if (name == null) {
        throw new NullPointerException("Null name");
      }
      this.name = name;
      return this;
    }
    @Override
    public PluginModel.Builder setIgnored(boolean ignored) {
      this.ignored = ignored;
      return this;
    }
    @Override
    public PluginModel.Builder setEnabled(boolean enabled) {
      this.enabled = enabled;
      return this;
    }
    @Override
    public PluginModel build() {
      String missing = "";
      if (this.name == null) {
        missing += " name";
      }
      if (this.ignored == null) {
        missing += " ignored";
      }
      if (this.enabled == null) {
        missing += " enabled";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_PluginModel(
          this.name,
          this.ignored,
          this.enabled);
    }
  }

}
