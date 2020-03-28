package io.github.pinkteammodfest.railbot.robot;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.registry.RailbotRegistry;
import net.minecraft.util.registry.Registry;

public class RailbotFeatures {

  public static final RobotFeatureType<?> EMPTY = register("empty", new RobotFeatureType<>(RobotFeature::new));
  public static final RobotFeatureType<?> PLACE_RAIL = register("place_rail", new RobotFeatureType<>(RobotFeature::new));

  public static void init() {
    // NO-OP
  }

  static <T extends RobotFeatureType<?>> T register(String name, T feature) {
    return Registry.register(RailbotRegistry.FEATURE, Railbot.id(name), feature);
  }
}
