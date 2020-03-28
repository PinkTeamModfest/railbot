package io.github.pinkteammodfest.railbot.block;

import io.github.pinkteammodfest.railbot.block.entity.CoalGeneratorBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CoalGeneratorBlock extends BlockWithEntity {
    public CoalGeneratorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new CoalGeneratorBlockEntity();
    }
}


