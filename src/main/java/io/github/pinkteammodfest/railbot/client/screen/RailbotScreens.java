package io.github.pinkteammodfest.railbot.client.screen;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.block.entity.CoalGeneratorBlockEntity;
import io.github.pinkteammodfest.railbot.container.CoalGeneratorContainer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.block.entity.BlockEntity;

public class RailbotScreens {

    public static final String COAL_GENERATOR_ID = "coal_generator_container";

    public static void init() {
        ScreenProviderRegistry.INSTANCE.registerFactory(Railbot.id(COAL_GENERATOR_ID), (syncId, identifier, player, buf) -> {
            BlockEntity blockEntity = player.world.getBlockEntity(buf.readBlockPos());
            if(blockEntity instanceof  CoalGeneratorBlockEntity) {
                return new CoalGeneratorScreen(new CoalGeneratorContainer(syncId, player.inventory, (CoalGeneratorBlockEntity) blockEntity), player.inventory);
            } else { // should only occur on malformed packets
                return new CoalGeneratorScreen(new CoalGeneratorContainer(syncId, player.inventory, new CoalGeneratorBlockEntity()), player.inventory);
            }
        });
    }

    private RailbotScreens() {
    }
}
