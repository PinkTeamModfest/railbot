package io.github.pinkteammodfest.railbot.container;

import io.github.pinkteammodfest.railbot.block.entity.GeneratorBlockEntity;
import net.minecraft.container.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class GeneratorContainer extends Container {

    private final GeneratorBlockEntity inventory;

    public GeneratorContainer(int syncId, PlayerInventory playerInventory, GeneratorBlockEntity inventory) {
        super(null, syncId);

        this.inventory = inventory;


        // TODO this should be restricted to fuel
        this.addSlot(new Slot(inventory, 0, 56, 53));

        int k;
        for (k = 0; k < 3; ++k) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for (k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }


    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUseInv(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        // TODO this probably needs to handle more cases
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();

            if (invSlot != 0) {
                if (!this.insertItem(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.insertItem(itemStack2, 1, 37, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    public GeneratorBlockEntity getBlockEntity() {
        return this.inventory;
    }

}
