package io.github.pinkteammodfest.railbot.entity;

import io.github.pinkteammodfest.railbot.network.RailbotNetworking;
import io.github.pinkteammodfest.railbot.network.RobotSpawnPacket;
import io.github.pinkteammodfest.railbot.robot.RailbotCores;
import io.github.pinkteammodfest.railbot.robot.RobotCore;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.util.Tickable;
import net.minecraft.world.World;
import team.reborn.energy.Energy;

public class RobotEntity extends Entity {

  private RobotCore core;

  public RobotEntity(World world) {
    super(RailbotEntities.ROBOT, world);

    core = RailbotCores.FURNACE_CORE.create();
  }

  public RobotEntity(World world, RobotCore core) {
    super(RailbotEntities.ROBOT, world);

    this.core = core;
  }

  public RobotEntity(World world, double x, double y, double z, RobotCore core) {
    super(RailbotEntities.ROBOT, world);

    setPos(x, y, z);
    this.core = core;
  }

  public RobotEntity(EntityType<? extends Entity> type, World world) {
    super(type, world);

    core = RailbotCores.FURNACE_CORE.create();
  }

  @Override
  public void tick() {
    super.tick();

    if (core instanceof Tickable) {
      ((Tickable) core).tick();
    }
  }

  public RobotCore getCore() {
    return core;
  }

  public void setCore(RobotCore core) {
    this.core = core;
  }

  public double getCoreEnergy() {
    return Energy.of(core).getEnergy();
  }

  public double getMaxCoreEnergy() {
    return Energy.of(core).getMaxStored();
  }

  public void setCoreEnergy(double amount) {
    Energy.of(core).set(amount);
  }

  // Returns how much energy was actually inserted
  public double insertCoreEnergy(double amount) {
    return Energy.of(core).extract(amount);
  }

  // Returns how much energy was actually extracted
  public double extractCoreEnergy(double amount) {
    return Energy.of(core).extract(amount);
  }

  @Override
  public void initDataTracker() {

  }

  @Override
  public void readCustomDataFromTag(CompoundTag tag) {
    CompoundTag coreTag = tag.getCompound("core");
    core = new RobotCore(coreTag);
  }

  @Override
  public void writeCustomDataToTag(CompoundTag tag) {
    CompoundTag coreTag = core.toTag(new CompoundTag());
    tag.put("core", coreTag);
  }

  @Override
  public Packet<?> createSpawnPacket() {
    RobotSpawnPacket packet = new RobotSpawnPacket(getEntityId(), getUuid(), getX(), getY(), getZ(), pitch, yaw, getType(), getCore().getType(), getCore().toTag(new CompoundTag()));
    return RailbotNetworking.toPacket(ServerSidePacketRegistry.INSTANCE, packet);
  }
}
