package net.fabricmc.splash.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.splash.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.options.AttackIndicator;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

import static java.awt.Color.HSBtoRGB;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(InGameHud.class)
public class HotbarMixin extends DrawableHelper {
    MinecraftClient client;
    private static final Identifier WIDGETS_TEXTURE = new Identifier("textures/gui/widgets.png");
    private int scaledWidth;
    private int scaledHeight;
    private ItemRenderer itemRenderer;



    @Inject(at = @At("HEAD"), method = "renderHotbar", cancellable = true)
    protected void renderHotbar(float tickDelta, MatrixStack matrices, CallbackInfo ci) {
        Color BACKGROUND = new Color(0, 0, 0, 125);
        Color SELECTION = new Color(0, 0, 0, 225);
        PlayerEntity playerEntity = this.getCameraPlayer();
        final double millis = System.currentTimeMillis() % 10000L / 10000.0f;
        final int color = HSBtoRGB((float) millis, 0.8f, 0.8f);
        float red = (color >> 16 & 255) / 255.0f;
        float green = (color >> 8 & 255) / 255.0f;
        float blue = (color & 255) / 255.0f;
        if (playerEntity != null) {
            if(ModConfig.transparent == true) {
                RenderSystem.color4f(0.5F, 0.5F, 0.5F, 0.5F);
            } else {
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            this.client.getTextureManager().bindTexture(WIDGETS_TEXTURE);
            ItemStack itemStack = playerEntity.getOffHandStack();
            Arm arm = playerEntity.getMainArm().getOpposite();
            int i = this.scaledWidth / 2;
            int j = this.getZOffset();
            boolean k = true;
            boolean l = true;
            this.setZOffset(-90);

            this.drawTexture(matrices, i - 91, this.scaledHeight - 22, 0, 0, 182, 22);
            this.drawTexture(matrices, i - 91 - 1 + playerEntity.inventory.selectedSlot * 20, this.scaledHeight - 22 - 1, 0, 22, 24, 22);
            //fill(matrices, i - 91 - 1 + playerEntity.inventory.selectedSlot * 20, this.scaledHeight - 22 - 1, i - 91 - 1 + playerEntity.inventory.selectedSlot * 20+24, scaledHeight, SELECTION.getRGB());
            if (!itemStack.isEmpty()) {
                if (arm == Arm.LEFT) {
                    this.drawTexture(matrices, i - 91 - 29, this.scaledHeight - 23, 24, 22, 29, 24);
                } else {
                    this.drawTexture(matrices, i + 91, this.scaledHeight - 23, 53, 22, 29, 24);
                }
            }

            this.setZOffset(j);
            RenderSystem.enableRescaleNormal();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
           // fill(matrices, 0, this.scaledHeight- 22, this.scaledWidth, scaledHeight, BACKGROUND.getRGB());
            int p;
            int q;
            int r;
            for(p = 0; p < 9; ++p) {
                q = i - 90 + p * 20 + 2;
                r = this.scaledHeight - 16 - 3;
                this.renderHotbarItem(q, r, tickDelta, playerEntity, (ItemStack)playerEntity.inventory.main.get(p));
            }

            if (!itemStack.isEmpty()) {
                p = this.scaledHeight - 16 - 3;
                if (arm == Arm.LEFT) {
                    this.renderHotbarItem(i - 91 - 26, p, tickDelta, playerEntity, itemStack);
                } else {
                    this.renderHotbarItem(i + 91 + 10, p, tickDelta, playerEntity, itemStack);
                }
            }

            if (this.client.options.attackIndicator == AttackIndicator.HOTBAR) {
                float f = this.client.player.getAttackCooldownProgress(0.0F);
                if (f < 1.0F) {
                    q = this.scaledHeight - 20;
                    r = i + 91 + 6;
                    if (arm == Arm.RIGHT) {
                        r = i - 91 - 22;
                    }

                    this.client.getTextureManager().bindTexture(DrawableHelper.GUI_ICONS_TEXTURE);
                    int s = (int)(f * 19.0F);
                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    this.drawTexture(matrices, r, q, 0, 94, 18, 18);
                    this.drawTexture(matrices, r, q + 18 - s, 18, 112 - s, 18, s);
                }
            }

            RenderSystem.disableRescaleNormal();
            RenderSystem.disableBlend();
        }
        ci.cancel();
    }
    private void renderHotbarItem(int x, int y, float tickDelta, PlayerEntity player, ItemStack stack) {
        if (!stack.isEmpty()) {
            float f = (float)stack.getCooldown() - tickDelta;
            if (f > 0.0F) {
                RenderSystem.pushMatrix();
                float g = 1.0F + f / 5.0F;
                RenderSystem.translatef((float)(x + 8), (float)(y + 12), 0.0F);
                RenderSystem.scalef(1.0F / g, (g + 1.0F) / 2.0F, 1.0F);
                RenderSystem.translatef((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }

            this.itemRenderer.renderInGuiWithOverrides(player, stack, x, y);
            if (f > 0.0F) {
                RenderSystem.popMatrix();
            }

            this.itemRenderer.renderGuiItemOverlay(this.client.textRenderer, stack, x, y);
        }
    }
    private PlayerEntity getCameraPlayer() {
        return !(this.client.getCameraEntity() instanceof PlayerEntity) ? null : (PlayerEntity)this.client.getCameraEntity();
    }
}