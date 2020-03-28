package io.github.pinkteammodfest.railbot.network;

import io.github.pinkteammodfest.railbot.registry.RailbotRegistry;
import io.github.pinkteammodfest.railbot.robot.RobotCore;
import io.github.pinkteammodfest.railbot.robot.RobotFeature;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.PacketByteBuf;

public class RailbotDataTrackers {

  public static final TrackedDataHandler<RobotCore> ROBOT_CORE = new TrackedDataHandler<RobotCore>() {
    @Override
    public void write(PacketByteBuf data, RobotCore object) {
      data.writeVarInt(RailbotRegistry.CORE.getRawId(object.getType()));
      data.writeCompoundTag(object.toTag(new CompoundTag())); // todo sync tag method
    }

    @Override
    public RobotCore read(PacketByteBuf packetByteBuf) {
      RobotCore core = RailbotRegistry.CORE.get(packetByteBuf.readVarInt()).create();
      core.fromTag(packetByteBuf.readCompoundTag());
      return core;
    }

    @Override
    public RobotCore copy(RobotCore object) {
      return object.copy();
    }
  };
  
  public static final TrackedDataHandler<RobotFeature> ROBOT_FEATURE = new TrackedDataHandler<RobotFeature>() {
    @Override
    public void write(PacketByteBuf data, RobotFeature object) {
      data.writeVarInt(RailbotRegistry.FEATURE.getRawId(object.getType()));
      data.writeCompoundTag(object.toTag(new CompoundTag())); // todo sync tag method
    }

    @Override
    public RobotFeature read(PacketByteBuf packetByteBuf) {
      RobotFeature feature = RailbotRegistry.FEATURE.get(packetByteBuf.readVarInt()).create();
      feature.fromTag(packetByteBuf.readCompoundTag());
      return feature;
    }

    @Override
    public RobotFeature copy(RobotFeature object) {
      return object.copy();
    }
  };

  static {
    TrackedDataHandlerRegistry.register(ROBOT_CORE);
    TrackedDataHandlerRegistry.register(ROBOT_FEATURE);
  }

}
