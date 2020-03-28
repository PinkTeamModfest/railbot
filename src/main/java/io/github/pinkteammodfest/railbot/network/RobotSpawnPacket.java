package io.github.pinkteammodfest.railbot.network;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.entity.RobotEntity;
import io.github.pinkteammodfest.railbot.registry.RailbotRegistry;
import io.github.pinkteammodfest.railbot.robot.RobotCore;
import io.github.pinkteammodfest.railbot.robot.RobotCoreType;
import java.util.UUID;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

public final class RobotSpawnPacket implements RailbotPacket {

  public static final Identifier ID = Railbot.id("robot_spawn");
  private final int id;
  private final UUID uuid;
  private final double x;
  private final double y;
  private final double z;
  private final int pitch;
  private final int yaw;
  private final EntityType<?> entityType;

  public RobotSpawnPacket(int id, UUID uuid, double x, double y, double z, float pitch, float yaw, EntityType<?> entityTypeId) {
    this.id = id;
    this.uuid = uuid;
    this.x = x;
    this.y = y;
    this.z = z;
    this.pitch = MathHelper.floor(pitch * 256.0F / 360.0F);
    this.yaw = MathHelper.floor(yaw * 256.0F / 360.0F);
    this.entityType = entityTypeId;
  }

  public RobotSpawnPacket(PacketByteBuf buf) {
    this.id = buf.readVarInt();
    this.uuid = buf.readUuid();
    this.entityType = Registry.ENTITY_TYPE.get(buf.readVarInt());
    this.x = buf.readDouble();
    this.y = buf.readDouble();
    this.z = buf.readDouble();
    this.pitch = buf.readByte();
    this.yaw = buf.readByte();
  }

  @Override
  public void toBuf(PacketByteBuf buf) {
    buf.writeVarInt(this.id);
    buf.writeUuid(this.uuid);
    buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(this.entityType));
    buf.writeDouble(this.x);
    buf.writeDouble(this.y);
    buf.writeDouble(this.z);
    buf.writeByte(this.pitch);
    buf.writeByte(this.yaw);
  }

  @Override
  public Identifier getChannel() {
    return ID;
  }

  @Override
  public void apply(PacketContext context) {
    ClientWorld world = MinecraftClient.getInstance().world;
    Entity entity = entityType.create(world);
    if (entity == null) {
      return;
    }
    entity.updateTrackedPosition(x, y, z);
    entity.pitch = (float) (pitch * 360) / 256.0F;
    entity.yaw = (float) (yaw * 360) / 256.0F;
    entity.setEntityId(id);
    entity.setUuid(uuid);
    world.addEntity(id, entity);
  }
}
