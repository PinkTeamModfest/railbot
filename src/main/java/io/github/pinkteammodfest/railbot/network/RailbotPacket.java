package io.github.pinkteammodfest.railbot.network;

import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public interface RailbotPacket {

  Identifier getChannel();

  void toBuf(PacketByteBuf buf);

  void apply(PacketContext context);
}
