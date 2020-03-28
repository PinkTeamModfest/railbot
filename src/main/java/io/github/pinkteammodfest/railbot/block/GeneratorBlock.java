package io.github.pinkteammodfest.railbot.block;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.block.entity.GeneratorBlockEntity;
import io.github.pinkteammodfest.railbot.container.RailbotContainers;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GeneratorBlock extends AbstractFurnaceBlock {


    public GeneratorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public void onBlockRemoved(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GeneratorBlockEntity) {
                ItemScatterer.spawn(world, pos, (GeneratorBlockEntity)blockEntity);
                world.updateHorizontalAdjacent(pos, this);
            }

            super.onBlockRemoved(state, world, pos, newState, moved);
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new GeneratorBlockEntity();
    }


    @Override
    protected void openContainer(World world, BlockPos pos, PlayerEntity player) {
        if (!world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(Railbot.id(RailbotContainers.GENERATOR_ID), player, (buf) -> buf.writeBlockPos(pos));
        }
    }

}


