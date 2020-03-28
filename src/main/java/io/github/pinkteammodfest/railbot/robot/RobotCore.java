package io.github.pinkteammodfest.railbot.robot;

import net.minecraft.nbt.CompoundTag;
import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyStorage;
import team.reborn.energy.EnergyTier;

public class RobotCore implements EnergyStorage {

  protected final RobotCoreType<?> type;
  private double energy = 0;

  public RobotCore(RobotCoreType<?> type) {
    this.type = type;
  }
  
  public RobotCoreType<?> getType() {
    return type;
  }

  @Override
  public double getStored(EnergySide energySide) {
    return energy;
  }

  @Override
  public void setStored(double v) {
    energy = v;
  }

  @Override
  public double getMaxStoredPower() {
    return 8000;
  }

  @Override
  public EnergyTier getTier() {
    return EnergyTier.LOW;
  }

  public CompoundTag toTag(CompoundTag compoundTag) {
    compoundTag.putDouble("energy", energy);

    return compoundTag;
  }

  public void fromTag(CompoundTag compoundTag) {
    energy = compoundTag.getDouble("energy");
  }
}
