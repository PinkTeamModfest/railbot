package io.github.pinkteammodfest.railbot;

import io.github.pinkteammodfest.railbot.block.RailbotBlocks;
import io.github.pinkteammodfest.railbot.block.entity.RailbotBlockEntities;
import io.github.pinkteammodfest.railbot.entity.RailbotEntities;
import io.github.pinkteammodfest.railbot.network.RailbotNetworking;
import io.github.pinkteammodfest.railbot.registry.RailbotRegistry;
import io.github.pinkteammodfest.railbot.robot.RailbotCores;
import io.github.pinkteammodfest.railbot.robot.RailbotFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Railbot implements ModInitializer {
  INSTANCE;

  public static final String ID = "railbot";

  public static final Logger LOGGER = LogManager.getLogger(ID);

  public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(RailbotBlocks.RAIL));

  public static Identifier id(String name) {
    return new Identifier(ID, name);
  }

  @Override
  public void onInitialize() {
    RailbotBlocks.init();
    RailbotRegistry.init();
    RailbotCores.init();
    RailbotFeatures.init();
    RailbotEntities.init();
    RailbotNetworking.handleServerboundPackets();
    RailbotBlockEntities.init();

  }
}
