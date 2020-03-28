package io.github.pinkteammodfest.railbot.client;

import io.github.pinkteammodfest.railbot.network.RailbotNetworking;
import net.fabricmc.api.ClientModInitializer;

public enum RailbotClient implements ClientModInitializer {
  INSTANCE;

  @Override
  public void onInitializeClient() {
    RailbotNetworking.handleClientboundPackets();
  }
}
