package io.github.pinkteammodfest.railbot.container.slot;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class FuelSlot extends Slot {
    public FuelSlot(Inventory inventory, int invSlot, int xPosition, int yPosition) {
        super(inventory, invSlot, xPosition, yPosition);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return AbstractFurnaceBlockEntity.canUseAsFuel(stack);
    }
}
