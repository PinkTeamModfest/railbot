package io.github.pinkteammodfest.railbot.entity;

import io.github.pinkteammodfest.railbot.network.RailbotDataTrackers;
import io.github.pinkteammodfest.railbot.network.RailbotNetworking;
import io.github.pinkteammodfest.railbot.network.RobotSpawnPacket;
import io.github.pinkteammodfest.railbot.registry.RailbotRegistry;
import io.github.pinkteammodfest.railbot.robot.RailbotCores;
import io.github.pinkteammodfest.railbot.robot.RailbotFeatures;
import io.github.pinkteammodfest.railbot.robot.RobotCore;
import io.github.pinkteammodfest.railbot.robot.RobotFeature;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.world.World;
import team.reborn.energy.Energy;

public class RobotEntity extends Entity {

  protected static final TrackedData<RobotCore> CORE = DataTracker.registerData(RobotEntity.class, RailbotDataTrackers.ROBOT_CORE);
  protected static final TrackedData<RobotFeature> FEATURE = DataTracker.registerData(RobotEntity.class, RailbotDataTrackers.ROBOT_FEATURE);

  public RobotEntity(EntityType<? extends Entity> type, World world) {
    super(type, world);
  }

  @Override
  public void tick() {
    super.tick();

    RobotCore core = getCore();
    if (core instanceof Tickable) {
      ((Tickable) core).tick();
    }
  }

  public RobotCore getCore() {
    return this.dataTracker.get(CORE);
  }

  public void setCore(RobotCore core) {
    this.dataTracker.set(CORE, core);
  }

  public RobotFeature getFeature() {
    return this.dataTracker.get(FEATURE);
  }

  public void setFeature(RobotFeature feature) {
    this.dataTracker.set(FEATURE, feature);
  }

  public double getCoreEnergy() {
    return Energy.of(getCore()).getEnergy();
  }

  public double getMaxCoreEnergy() {
    return Energy.of(getCore()).getMaxStored();
  }

  public void setCoreEnergy(double amount) {
    Energy.of(getCore()).set(amount);
  }

  // Returns how much energy was actually inserted
  public double insertCoreEnergy(double amount) {
    return Energy.of(getCore()).extract(amount);
  }

  // Returns how much energy was actually extracted
  public double extractCoreEnergy(double amount) {
    return Energy.of(getCore()).extract(amount);
  }

  @Override
  public void initDataTracker() {
    dataTracker.startTracking(CORE, RailbotCores.EMPTY.create());
    dataTracker.startTracking(FEATURE, RailbotFeatures.EMPTY.create());
  }

  @Override
  public void readCustomDataFromTag(CompoundTag tag) {
    CompoundTag coreTag = tag.getCompound("core");
    setCore(new RobotCore(coreTag));
    CompoundTag featureTag = tag.getCompound("feature");
    RobotFeature feature = RailbotRegistry.FEATURE.get(Identifier.tryParse(featureTag.getString("type"))).create();
    feature.fromTag(featureTag);
    setFeature(feature);
  }

  @Override
  public void writeCustomDataToTag(CompoundTag tag) {
    CompoundTag coreTag = getCore().toTag(new CompoundTag());
    tag.put("core", coreTag);
    RobotFeature feature = getFeature();
    CompoundTag featureTag = feature.toTag(new CompoundTag());
    featureTag.putString("type", RailbotRegistry.FEATURE.getId(feature.getType()).toString());
    tag.put("feature", featureTag);
  }

  @Override
  public Packet<?> createSpawnPacket() {
    RobotSpawnPacket packet = new RobotSpawnPacket(getEntityId(), getUuid(), getX(), getY(), getZ(), pitch, yaw, getType());
    return RailbotNetworking.toPacket(ServerSidePacketRegistry.INSTANCE, packet);
  }
}
