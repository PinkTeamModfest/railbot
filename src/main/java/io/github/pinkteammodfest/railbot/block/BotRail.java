package io.github.pinkteammodfest.railbot.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;

public interface BotRail {
  // todo expose bot interaction functionalities here!

  static boolean shouldConnect(IWorld world, BlockPos pos, BlockState state, Direction from) {
    Block block = state.getBlock();
    if (!(block instanceof BotRail)) {
      return false;
    }

    return ((BotRail) block).canConnect(world, pos, state, from);
  }

  boolean canConnect(IWorld world, BlockPos pos, BlockState state, Direction from);

}
