package io.github.pinkteammodfest.railbot;

import io.github.pinkteammodfest.railbot.block.RailbotBlocks;
import io.github.pinkteammodfest.railbot.network.RailbotNetworking;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Railbot implements ModInitializer {
  INSTANCE;

  public static final String ID = "railbot";

  public static final Logger LOGGER = LogManager.getLogger(ID);

  public static Identifier id(String name) {
    return new Identifier(ID, name);
  }

  @Override
  public void onInitialize() {
    RailbotBlocks.init();
    RailbotNetworking.handleServerboundPackets();
  }
}
