package io.github.pinkteammodfest.railbot.robot;

import net.minecraft.nbt.CompoundTag;

public class RobotFeature {
  
  protected final RobotFeatureType<?> type;

  public RobotFeature(RobotFeatureType<?> type) {
    this.type = type;
  }

  public RobotFeatureType<?> getType() {
    return type;
  }

  public CompoundTag toTag(CompoundTag compoundTag) {
    return compoundTag;
  }

  public void fromTag(CompoundTag compoundTag) {

  }
}
