package io.github.pinkteammodfest.railbot.item;

import io.github.pinkteammodfest.railbot.Railbot;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class RailbotItems {
	public static <T extends Item> T register(String name, T item) {
		return Registry.register(Registry.ITEM, Railbot.id(name), item);
	}

	public static void init() {}

	private RailbotItems() {}
}
