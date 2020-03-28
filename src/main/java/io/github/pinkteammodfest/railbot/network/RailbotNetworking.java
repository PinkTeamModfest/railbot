package io.github.pinkteammodfest.railbot.network;

import io.netty.buffer.Unpooled;
import java.util.function.Function;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.Util;

public final class RailbotNetworking {

  public static Packet<?> toPacket(PacketRegistry registry, RailbotPacket packet) {
    return registry.toPacket(packet.getChannel(), Util.make(new PacketByteBuf(Unpooled.buffer()), packet::toBuf));
  }

  public static void sendToServer(RailbotPacket packet) {
    ClientSidePacketRegistry.INSTANCE.sendToServer(packet.getChannel(), Util.make(new PacketByteBuf(Unpooled.buffer()), packet::toBuf));
  }

  public static void send(ServerPlayerEntity player, RailbotPacket packet) {
    ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, packet.getChannel(), Util.make(new PacketByteBuf(Unpooled.buffer()), packet::toBuf));
  }

  public static void handleClientboundPackets() {
    registerClientboundPacket(RobotSpawnPacket.ID, RobotSpawnPacket::new);
  }

  public static void handleServerboundPackets() {
  }

  public static <T extends RailbotPacket> void registerClientboundPacket(Identifier channel, Function<PacketByteBuf, RailbotPacket> reader) {
    ClientSidePacketRegistry.INSTANCE.register(channel, (context, buf) -> {
      RailbotPacket packet = reader.apply(buf);
      context.getTaskQueue().execute(() -> packet.apply(context));
    });
  }

  public static <T extends RailbotPacket> void registerServerboundPacket(Identifier channel, Function<PacketByteBuf, RailbotPacket> reader) {
    ServerSidePacketRegistry.INSTANCE.register(channel, (context, buf) -> {
      RailbotPacket packet = reader.apply(buf);
      context.getTaskQueue().execute(() -> packet.apply(context));
    });
  }

}
