package io.github.pinkteammodfest.railbot.block;

import java.util.EnumMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

// rotate by wrenching maybe
public class DepotRailBlock extends Block implements BotRail {

  public static final EnumProperty<Axis> AXIS = Properties.AXIS;
  private static final Map<Axis, VoxelShape> SHAPES;

  static {
    SHAPES = new EnumMap<>(Axis.class);
    double thickness = 5;
    double begin = (8 - (thickness / 2)) / 16;
    double end = (8 + (thickness / 2)) / 16;
    SHAPES.put(Axis.Y, VoxelShapes.cuboid(begin, 0, begin, end, 1, end));
    SHAPES.put(Axis.X, VoxelShapes.cuboid(0, begin, begin, 1, end, end));
    SHAPES.put(Axis.Z, VoxelShapes.cuboid(begin, begin, 0, end, end, 1));
  }

  public DepotRailBlock(Settings settings) {
    super(settings);

    setDefaultState(getDefaultState().with(AXIS, Axis.X));
  }

  @Override
  protected void appendProperties(Builder<Block, BlockState> builder) {
    super.appendProperties(builder);
    builder.add(AXIS);
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos,
      EntityContext context) {
    return SHAPES.get(state.get(AXIS));
  }

  @Override
  public boolean canConnect(IWorld world, BlockPos pos, BlockState state, Direction from) {
    return from.getAxis() == state.get(AXIS); // todo broke
  }
}
