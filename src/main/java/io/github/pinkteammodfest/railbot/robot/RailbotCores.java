package io.github.pinkteammodfest.railbot.robot;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.registry.RailbotRegistry;
import net.minecraft.util.registry.Registry;

public class RailbotCores {

  public static void init() {
    // NO-OP
  }

  static <T extends RobotCore> T register(String name, T core) {
    return Registry.register(RailbotRegistry.CORE, Railbot.id(name), core);
  }
}
