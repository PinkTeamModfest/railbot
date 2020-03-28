package io.github.pinkteammodfest.railbot.registry;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.robot.RobotCoreType;
import io.github.pinkteammodfest.railbot.robot.RobotFeatureType;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;

public final class RailbotRegistry {

  public static DefaultedRegistry<RobotCoreType<?>> CORE = register("core", new DefaultedRegistry<>(Railbot.id("empty").toString()));
  public static DefaultedRegistry<RobotFeatureType<?>> FEATURE = register("feature", new DefaultedRegistry<>(Railbot.id("empty").toString()));

  private static <R extends MutableRegistry<?>> R register(String registryName, R mutableRegistry) {
    return Registry.REGISTRIES.add(Railbot.id(registryName), mutableRegistry);
  }

  public static void init() {}
}
