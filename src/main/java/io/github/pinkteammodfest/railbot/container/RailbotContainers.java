package io.github.pinkteammodfest.railbot.container;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.block.entity.CoalGeneratorBlockEntity;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;


public class RailbotContainers {


    public static final String COAL_GENERATOR_ID = "coal_generator_container";


    public static void init() {
        ContainerProviderRegistry.INSTANCE.registerFactory(Railbot.id(COAL_GENERATOR_ID), (syncId, identifier, player, buf) -> {
            // TODO figure out appropriate fail state for this
            return new CoalGeneratorContainer(syncId, player.inventory, (CoalGeneratorBlockEntity) player.world.getBlockEntity(buf.readBlockPos()));
        });
    }

    private RailbotContainers() {
    }


}
