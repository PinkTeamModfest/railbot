package io.github.pinkteammodfest.railbot.registry;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.robot.RobotCore;
import io.github.pinkteammodfest.railbot.robot.RobotFeature;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;

public class RailbotRegistry {

  public static DefaultedRegistry<RobotCore> CORE = register("core", new DefaultedRegistry<>("furnace_core"));
  public static DefaultedRegistry<RobotFeature> FEATURE = register("feature", new DefaultedRegistry<>("place_rail"));

  private static <T, R extends MutableRegistry<T>> R register(String registryName, R mutableRegistry) {
    Identifier registryIdentifier = new Identifier(Railbot.ID, registryName);
    return Registry.REGISTRIES.add(registryIdentifier, mutableRegistry);
  }
}
