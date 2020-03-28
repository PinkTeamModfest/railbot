package io.github.pinkteammodfest.railbot.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyStorage;
import team.reborn.energy.EnergyTier;

public class CoalGeneratorBlockEntity extends BlockEntity implements Inventory, EnergyStorage {

    private final static double MAX_ENERGY = 10000; // TODO placeholder number

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    private double energy = 0;

    public CoalGeneratorBlockEntity() {
        super(RailbotBlockEntities.COAL_GENERATOR);
    }

    @Override
    public int getInvSize() {
        return this.inventory.size();
    }

    @Override
    public boolean isInvEmpty() {
        return this.inventory.isEmpty();
    }

    @Override
    public ItemStack getInvStack(int slot) {
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack takeInvStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeInvStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setInvStack(int slot, ItemStack stack) {
        ItemStack itemStack = (ItemStack) this.inventory.get(slot);
        boolean bl = !stack.isEmpty() && stack.isItemEqualIgnoreDamage(itemStack) && ItemStack.areTagsEqual(stack, itemStack);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getInvMaxStackAmount()) {
            stack.setCount(this.getInvMaxStackAmount());
        }
        // TODO handle burn time updating and such
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return player.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag, inventory);
        energy = tag.getDouble("energy");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, inventory);
        tag.putDouble("energy", energy);
        return super.toTag(tag);
    }

    @Override
    public double getStored(EnergySide energySide) {
        return energy;
    }

    @Override
    public void setStored(double v) {
        energy = Math.min(v, MAX_ENERGY);
    }

    @Override
    public double getMaxStoredPower() {
        return MAX_ENERGY;
    }

    @Override
    public EnergyTier getTier() {
        return EnergyTier.LOW; // TODO placeholder
    }
}
