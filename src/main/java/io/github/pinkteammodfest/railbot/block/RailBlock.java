package io.github.pinkteammodfest.railbot.block;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class RailBlock extends Block implements BotRail {

  private static final Map<Direction, BooleanProperty> CONNECTIONS;
  private static final ArrayList<VoxelShape> SHAPES;

  static {
    CONNECTIONS = new EnumMap<>(Direction.class);
    for (Direction each : Direction.values()) {
      CONNECTIONS.put(each, BooleanProperty.of(each.getName()));
    }

    double thickness = 5;
    double begin = (8 - (thickness / 2)) / 16;
    double end = (8 + (thickness / 2)) / 16;
    SHAPES = new ArrayList<>(7);
    SHAPES.add(0, VoxelShapes.cuboid(begin, 0, begin, end, begin, end));
    SHAPES.add(1, VoxelShapes.cuboid(begin, end, begin, end, 1, end));
    SHAPES.add(2, VoxelShapes.cuboid(begin, begin, 0, end, end, begin));
    SHAPES.add(3, VoxelShapes.cuboid(begin, begin, end, end, end, 1));
    SHAPES.add(4, VoxelShapes.cuboid(0, begin, begin, begin, end, end));
    SHAPES.add(5, VoxelShapes.cuboid(end, begin, begin, 1, end, end));
    SHAPES.add(6, VoxelShapes.cuboid(begin, begin, begin, end, end, end));
  }

  public static BooleanProperty getConnectionProperty(Direction direction) {
    return CONNECTIONS.get(direction);
  }

  public RailBlock(Settings settings) {
    super(settings);

    BlockState defaultState = getDefaultState();
    for (BooleanProperty each : CONNECTIONS.values()) {
      defaultState = defaultState.with(each, false);
    }
    setDefaultState(defaultState);
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos,
      EntityContext context) {
    VoxelShape shape = SHAPES.get(6);

    for (Direction each : CONNECTIONS.keySet()) {
      if (state.get(getConnectionProperty(each))) {
        shape = VoxelShapes.union(shape, SHAPES.get(each.getId()));
      }
    }

    return shape;
  }

  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    World world = ctx.getWorld();
    BlockPos pos = ctx.getBlockPos();
    BlockPos.Mutable neighborPos = new Mutable();
    BlockState state = getDefaultState();
    for (Map.Entry<Direction, BooleanProperty> entry : CONNECTIONS.entrySet()) {
      Direction direction = entry.getKey();
      neighborPos.set(pos).setOffset(direction);
      BooleanProperty property = entry.getValue();
      state = state.with(property, BotRail.shouldConnect(world, neighborPos, world.getBlockState(neighborPos), direction.getOpposite()));
    }
    return state;
  }

  @Override
  public BlockState getStateForNeighborUpdate(BlockState state, Direction facing,
      BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
    return state.with(getConnectionProperty(facing),
        BotRail.shouldConnect(world, neighborPos, neighborState, facing.getOpposite()));
  }

  @Override
  protected void appendProperties(Builder<Block, BlockState> builder) {
    super.appendProperties(builder);
    builder.add(RailBlock.CONNECTIONS.values().toArray(new Property<?>[0]));
  }

  @Override
  public boolean canConnect(IWorld world, BlockPos pos, BlockState state, Direction from) {
    return true;
  }
}
