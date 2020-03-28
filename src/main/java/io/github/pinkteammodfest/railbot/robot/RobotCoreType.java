package io.github.pinkteammodfest.railbot.robot;

import java.util.function.Function;

public class RobotCoreType<T extends RobotCore> {

  protected final Function<RobotCoreType<T>, T> factory;

  public RobotCoreType(Function<RobotCoreType<T>, T> factory) {
    this.factory = factory;
  }

  public T create() {
    return factory.apply(this);
  }
}
