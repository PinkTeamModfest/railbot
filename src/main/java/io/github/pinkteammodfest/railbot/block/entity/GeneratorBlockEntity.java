package io.github.pinkteammodfest.railbot.block.entity;

import io.github.pinkteammodfest.railbot.block.GeneratorBlock;
import io.github.pinkteammodfest.railbot.block.RailbotBlocks;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import team.reborn.energy.EnergySide;
import team.reborn.energy.EnergyStorage;
import team.reborn.energy.EnergyTier;

public class GeneratorBlockEntity extends BlockEntity implements Inventory, EnergyStorage, Tickable, BlockEntityClientSerializable {

    private final static double MAX_ENERGY = 10000; // TODO placeholder numbers
    private final static double ENERGY_PER_TICK = 2;

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    private double energy = 0;
    private double burnTime = 0;
    private double startBurnTime = 0;

    public GeneratorBlockEntity() {
        super(RailbotBlockEntities.GENERATOR);
    }


    // Methods for IInventory

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
        ItemStack itemStack = this.inventory.get(slot);
        boolean bl = !stack.isEmpty() && stack.isItemEqualIgnoreDamage(itemStack) && ItemStack.areTagsEqual(stack, itemStack);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getInvMaxStackAmount()) {
            stack.setCount(this.getInvMaxStackAmount());
        }
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity player) {
        if (this.world != null && this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return player.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    // Methods for BlockEntity

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag, inventory);
        energy = tag.getDouble("energy");
        burnTime = tag.getDouble("burntime");
        startBurnTime = tag.getDouble("startburntime");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, inventory);
        tag.putDouble("energy", energy);
        tag.putDouble("burntime", burnTime);
        tag.putDouble("startburntime", startBurnTime);
        return super.toTag(tag);
    }


    // Methods for Tickable

    @Override
    public void tick() {
        if (this.world != null && !this.world.isClient) {
            boolean burning = burnTime > 0;
            if (burning) {
                setStored(getStored(null) + ENERGY_PER_TICK);
                this.burnTime -= 1;
                if(this.burnTime == 0 ) {
                    this.world.setBlockState(this.pos, RailbotBlocks.GENERATOR.getDefaultState().with(GeneratorBlock.ON, false));
                }
            } else if (getStored(null) <= getMaxStoredPower() && !this.inventory.get(0).isEmpty()) {
                ItemStack fuel = this.inventory.get(0);
                burnTime = AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(fuel.getItem(), 0);
                fuel.decrement(1);
                this.inventory.set(0, fuel);
                this.startBurnTime = burnTime;
                this.world.setBlockState(this.pos, RailbotBlocks.GENERATOR.getDefaultState().with(GeneratorBlock.ON, true));

            }
            this.markDirty();
            this.sync();
        }

    }

    // Methods for BlockEntityClientSerializable

    @Override
    public void fromClientTag(CompoundTag tag) {
        this.energy = tag.getDouble("energy");
        this.burnTime = tag.getDouble("burntime");
        this.startBurnTime = tag.getDouble("startburntime");
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        tag.putDouble("energy", energy);
        tag.putDouble("burntime", burnTime);
        tag.putDouble("startburntime", startBurnTime);
        return tag;
    }

    // Methods for EnergyStorage

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

    // Getters/Setters

    public boolean isBurning() {
        return burnTime > 0;
    }

    public double getBurnTime() {
        return burnTime;
    }

    public void setBurnTime(double newBurnTime) {
        burnTime = newBurnTime;
    }

    public double getStartBurnTime() {
        return startBurnTime;
    }


}
