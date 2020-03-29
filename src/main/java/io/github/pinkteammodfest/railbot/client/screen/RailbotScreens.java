package io.github.pinkteammodfest.railbot.client.screen;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.block.entity.GeneratorBlockEntity;
import io.github.pinkteammodfest.railbot.container.GeneratorContainer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.block.entity.BlockEntity;

public class RailbotScreens {

  public static final String GENERATOR_ID = "generator_container";

  public static void init() {
    ScreenProviderRegistry.INSTANCE
        .registerFactory(Railbot.id(GENERATOR_ID), (syncId, identifier, player, buf) -> {
          BlockEntity blockEntity = player.world.getBlockEntity(buf.readBlockPos());
          if (blockEntity instanceof GeneratorBlockEntity) {
            return new GeneratorScreen(new GeneratorContainer(syncId, player.inventory,
                (GeneratorBlockEntity) blockEntity), player.inventory);
          } else { // should only occur on malformed packets
            return new GeneratorScreen(
                new GeneratorContainer(syncId, player.inventory, new GeneratorBlockEntity()),
                player.inventory);
          }
        });
  }

  private RailbotScreens() {
  }
}
