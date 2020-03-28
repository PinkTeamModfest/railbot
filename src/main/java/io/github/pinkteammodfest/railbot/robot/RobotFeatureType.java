package io.github.pinkteammodfest.railbot.robot;

import java.util.function.Function;

public class RobotFeatureType<T extends RobotFeature> {

  protected final Function<RobotFeatureType<T>, T> factory;

  public RobotFeatureType(Function<RobotFeatureType<T>, T> factory) {
    this.factory = factory;
  }

  public T create() {
    return factory.apply(this);
  }
}
