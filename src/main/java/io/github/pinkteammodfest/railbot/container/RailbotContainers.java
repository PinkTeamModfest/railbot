package io.github.pinkteammodfest.railbot.container;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.block.entity.GeneratorBlockEntity;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.entity.BlockEntity;


public class RailbotContainers {


  public static final String GENERATOR_ID = "generator_container";


  public static void init() {
    ContainerProviderRegistry.INSTANCE
        .registerFactory(Railbot.id(GENERATOR_ID), (syncId, identifier, player, buf) -> {
          BlockEntity blockEntity = player.world.getBlockEntity(buf.readBlockPos());
          if (blockEntity instanceof GeneratorBlockEntity) {
            return new GeneratorContainer(syncId, player.inventory,
                (GeneratorBlockEntity) blockEntity);
          } else { // should only occur on malformed packets
            return new GeneratorContainer(syncId, player.inventory, new GeneratorBlockEntity());
          }
        });
  }

  private RailbotContainers() {
  }


}
