package io.github.pinkteammodfest.railbot.robot;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.registry.RailbotRegistry;
import net.minecraft.util.registry.Registry;

public final class RailbotCores {
  
  public static final RobotCoreType<RobotCore> EMPTY = register("empty", new RobotCoreType<>(RobotCore::new));
  public static final RobotCoreType<RobotCore> FURNACE_CORE = register("furnace_core", new RobotCoreType<>(RobotCore::new));

  public static void init() {
    // NO-OP
  }

  static <T extends RobotCoreType<?>> T register(String name, T core) {
    return Registry.register(RailbotRegistry.CORE, Railbot.id(name), core);
  }
}
