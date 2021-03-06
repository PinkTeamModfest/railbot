package io.github.pinkteammodfest.railbot.block.entity;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.block.RailbotBlocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class RailbotBlockEntities {

  public static final BlockEntityType<GeneratorBlockEntity> GENERATOR = register("coal_generator",
      BlockEntityType.Builder.create(GeneratorBlockEntity::new, RailbotBlocks.GENERATOR)
          .build(null));


  private static <T extends BlockEntity> BlockEntityType<T> register(String name,
      BlockEntityType<T> blockEntity) {
    return Registry.register(Registry.BLOCK_ENTITY_TYPE, Railbot.id(name), blockEntity);
  }

  public static void init() {
  }

  private RailbotBlockEntities() {
  }
}
