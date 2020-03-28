package io.github.pinkteammodfest.railbot.robot;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.registry.RailbotRegistry;
import net.minecraft.util.registry.Registry;

public class RailbotFeatures {

  public static void init() {
    // NO-OP
  }

  static <T extends RobotFeature> T register(String name, T feature) {
    return Registry.register(RailbotRegistry.FEATURE, Railbot.id(name), feature);
  }
}
