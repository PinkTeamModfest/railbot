package io.github.pinkteammodfest.railbot.client;

import io.github.pinkteammodfest.railbot.client.renderer.RobotEntityRenderer;
import io.github.pinkteammodfest.railbot.client.screen.RailbotScreens;
import io.github.pinkteammodfest.railbot.entity.RailbotEntities;
import io.github.pinkteammodfest.railbot.network.RailbotNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public enum RailbotClient implements ClientModInitializer {
  INSTANCE;

  @Override
  public void onInitializeClient() {
    RailbotNetworking.handleClientboundPackets();

    EntityRendererRegistry.INSTANCE.register(RailbotEntities.ROBOT, ((entityRenderDispatcher, context) -> new RobotEntityRenderer(entityRenderDispatcher)));
    RailbotScreens.init();
  }
}
