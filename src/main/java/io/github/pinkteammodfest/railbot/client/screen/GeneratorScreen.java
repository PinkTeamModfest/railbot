package io.github.pinkteammodfest.railbot.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.pinkteammodfest.railbot.Railbot;
import io.github.pinkteammodfest.railbot.container.GeneratorContainer;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;


public class GeneratorScreen extends ContainerScreen<GeneratorContainer> {

  private static final Identifier BG_TEX = Railbot.id("textures/gui/generator.png");
  private final GeneratorContainer container;

  public GeneratorScreen(GeneratorContainer container, PlayerInventory inventory) {
    super(container, inventory, new LiteralText("Generator"));
    this.container = container;
  }

  @Override
  protected void drawBackground(float delta, int mouseX, int mouseY) {
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.minecraft.getTextureManager().bindTexture(GeneratorScreen.BG_TEX);
    this.blit(this.x, this.y, 0, 0, this.containerWidth, this.containerHeight);
    if (container.getBlockEntity().isBurning()) {
      int burnProgress = (int) Math.round(
          (container.getBlockEntity().getBurnTime() / container.getBlockEntity().getStartBurnTime())
              * 13);
      this.blit(this.x + 56, this.y + 36 + 12 - burnProgress, 176, 12 - burnProgress, 14,
          burnProgress + 1);
    }

    int chargeRatio = (int) Math.round(
        (container.getBlockEntity().getStored(null) / container.getBlockEntity()
            .getMaxStoredPower()) * 28);
    this.blit(this.x + 30, this.y + 16 + 30 - chargeRatio, 176, 42 - chargeRatio, 15,
        chargeRatio + 1);

  }

  @Override
  protected void drawForeground(int mouseX, int mouseY) {
    super.drawForeground(mouseX, mouseY);
    if (mouseX - this.x > 28 && mouseX - this.x < 46 && mouseY - this.y > 16
        && mouseY - this.y < 50) {
      renderTooltip(String.valueOf(container.getBlockEntity().getStored(null)), mouseX - this.x,
          mouseY - this.y);
    }
  }
}
