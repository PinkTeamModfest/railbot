package io.github.pinkteammodfest.railbot.container.slot;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import team.reborn.energy.EnergyStorage;

public class BatterySlot extends Slot {
    public BatterySlot(Inventory inventory, int invSlot, int xPosition, int yPosition) {
        super(inventory, invSlot, xPosition, yPosition);
    }
    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof EnergyStorage; // TODO make sure this works when we have a battery
    }
}
