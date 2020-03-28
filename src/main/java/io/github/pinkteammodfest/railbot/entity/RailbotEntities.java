package io.github.pinkteammodfest.railbot.entity;

import io.github.pinkteammodfest.railbot.Railbot;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

public final class RailbotEntities {

  public static final EntityType<RobotEntity> ROBOT = register("robot", FabricEntityTypeBuilder
      .create(EntityCategory.MISC, (EntityType.EntityFactory<RobotEntity>) RobotEntity::new).size(EntityDimensions.fixed(0.5F, 0.5F)).build());

  static <T extends Entity> EntityType<T> register(String name, EntityType<T> entity) {
    return Registry.register(Registry.ENTITY_TYPE, Railbot.id(name), entity);
  }

  public static void init() {}

  private RailbotEntities() {}
}
