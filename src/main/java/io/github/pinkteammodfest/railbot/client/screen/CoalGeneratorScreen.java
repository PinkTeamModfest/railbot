package io.github.pinkteammodfest.railbot.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.container.CoalGeneratorContainer;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class CoalGeneratorScreen extends ContainerScreen<CoalGeneratorContainer> {
    private static final Identifier BG_TEX = Railbot.id("textures/gui/coal_generator.png");

    public CoalGeneratorScreen(CoalGeneratorContainer container, PlayerInventory inventory) {
        super(container, inventory, new LiteralText("Coal Generator"));
    }

    @Override
    protected void drawBackground(float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(CoalGeneratorScreen.BG_TEX);
        this.blit(this.x, this.y, 0, 0, this.containerWidth, this.containerHeight);
    }
}
