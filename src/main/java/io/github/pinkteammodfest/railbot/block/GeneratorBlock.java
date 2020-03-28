package io.github.pinkteammodfest.railbot.block;

import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.block.entity.GeneratorBlockEntity;
import io.github.pinkteammodfest.railbot.container.RailbotContainers;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GeneratorBlock extends BlockWithEntity {

    public static final BooleanProperty ON = BooleanProperty.of("on");

    public GeneratorBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(GeneratorBlock.ON, false));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(GeneratorBlock.ON);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new GeneratorBlockEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(Railbot.id(RailbotContainers.GENERATOR_ID), player, (buf) -> buf.writeBlockPos(pos));
        }
        return ActionResult.SUCCESS;
    }
}


