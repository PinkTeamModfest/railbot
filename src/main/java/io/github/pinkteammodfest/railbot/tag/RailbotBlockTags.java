package io.github.pinkteammodfest.railbot.tag;

import io.github.pinkteammodfest.railbot.Railbot;
import net.fabricmc.fabric.impl.tag.extension.TagDelegate;
import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;

public final class RailbotBlockTags {

  public static final Tag<Block> RAIL = new TagDelegate<>(Railbot.id("rail"), BlockTags::getContainer);
  public static final Tag<Block> RAIL_CONNECTABLE = new TagDelegate<>(Railbot.id("rail_connectable"), BlockTags::getContainer);

  private RailbotBlockTags() {}
}
