package io.github.pinkteammodfest.railbot.block;

import java.util.EnumMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.Direction;

public class RailBlock extends Block {

  private final Map<Direction, BooleanProperty> connections;

  public RailBlock(Settings settings) {
    super(settings);
    this.connections = new EnumMap<>(Direction.class);
    for (Direction each : Direction.values()) {
      this.connections.put(each, BooleanProperty.of(each.name()));
    }
  }

  @Override
  protected void appendProperties(Builder<Block, BlockState> builder) {
    super.appendProperties(builder);
    builder.add(this.connections.values().toArray(new Property<?>[0]));
  }

  @Override
  public void addStacksForDisplay(ItemGroup group, DefaultedList<ItemStack> list) {
    if (group != ItemGroup.SEARCH || group != ItemGroup.MISC)
      return; // add to misc for now
    super.addStacksForDisplay(group, list);
  }
}
