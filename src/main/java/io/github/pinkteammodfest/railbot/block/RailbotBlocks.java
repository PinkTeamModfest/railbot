package io.github.pinkteammodfest.railbot.block;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.item.RailbotItems;
import java.util.function.Function;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public final class RailbotBlocks {

  public static final Block RAIL = register("rail", new RailBlock(
      FabricBlockSettings.of(Material.ANVIL)
          .hardness(5)
          .nonOpaque()
          .resistance(10)
          .build()
  ));

  public static final Block COAL_GENERATOR = register("coal_generator", new CoalGeneratorBlock(
          FabricBlockSettings.of(Material.STONE)
                  .hardness(5)
                  .resistance(10)
                  .build()
  ));


  static <T extends Block> T register(String name, T block, Item.Settings settings) {
    return register(name, block, new BlockItem(block, settings));
  }

  static <T extends Block> T register(String name, T block) {
    return register(name, block, new Item.Settings().group(Railbot.GROUP));
  }

  static <T extends Block> T register(String name, T block, Function<T, BlockItem> itemFactory) {
    return register(name, block, itemFactory.apply(block));
  }

  static <T extends Block> T register(String name, T block, BlockItem item) {
    T b = Registry.register(Registry.BLOCK, Railbot.id(name), block);
    if (item != null) {
      RailbotItems.register(name, item);
    }
    return b;
  }

  public static void init() {}

  private RailbotBlocks() {}
}
