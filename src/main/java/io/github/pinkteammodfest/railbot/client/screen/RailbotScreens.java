package io.github.pinkteammodfest.railbot.client.screen;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.block.entity.CoalGeneratorBlockEntity;
import io.github.pinkteammodfest.railbot.container.CoalGeneratorContainer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;

public class RailbotScreens {

    public static final String COAL_GENERATOR_ID = "coal_generator_container";

    public static void init() {
        ScreenProviderRegistry.INSTANCE.registerFactory(Railbot.id(COAL_GENERATOR_ID), (syncId, identifier, player, buf) -> {
            // TODO figure out appropriate fail state for this
            return new CoalGeneratorScreen(new CoalGeneratorContainer(syncId, player.inventory, (CoalGeneratorBlockEntity) player.world.getBlockEntity(buf.readBlockPos())), player.inventory);
        });
    }

    private RailbotScreens() {
    }
}
