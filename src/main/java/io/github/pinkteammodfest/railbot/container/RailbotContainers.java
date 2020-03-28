package io.github.pinkteammodfest.railbot.container;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.block.entity.CoalGeneratorBlockEntity;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.entity.BlockEntity;


public class RailbotContainers {


    public static final String COAL_GENERATOR_ID = "coal_generator_container";


    public static void init() {
        ContainerProviderRegistry.INSTANCE.registerFactory(Railbot.id(COAL_GENERATOR_ID), (syncId, identifier, player, buf) -> {
            BlockEntity blockEntity = player.world.getBlockEntity(buf.readBlockPos());
            if(blockEntity instanceof  CoalGeneratorBlockEntity) {
                return new CoalGeneratorContainer(syncId, player.inventory, (CoalGeneratorBlockEntity) blockEntity);
            } else { // should only occur on malformed packets
                return new CoalGeneratorContainer(syncId, player.inventory, new CoalGeneratorBlockEntity());
            }
        });
    }

    private RailbotContainers() {
    }


}
