package io.github.pinkteammodfest.railbot.robot.power;

public final class PowerTransmissionResult<P> {
  
  private final P first;
  private final P second;
  private final boolean changed;
  
  public PowerTransmissionResult(P first, P second, boolean changed) {
    this.first = first;
    this.second = second;
    this.changed = changed;
  }

  public P getFirst() {
    return first;
  }

  public P getSecond() {
    return second;
  }

  public boolean isChanged() {
    return changed;
  }
}
