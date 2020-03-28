package io.github.pinkteammodfest.railbot.block;

import io.github.pinkteammodfest.railbot.Railbot;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;

public final class RailbotBlocks {

  public static final Block RAIL = register("rail", new RailBlock(
      FabricBlockSettings.of(Material.ANVIL)
          .hardness(5)
          .nonOpaque()
          .resistance(10)
          .build()
  ));

  private static <T extends Block> T register(String name, T block) {
    return Registry.register(Registry.BLOCK, Railbot.id(name), block);
  }

  public static void init() {}

  private RailbotBlocks() {}
}
