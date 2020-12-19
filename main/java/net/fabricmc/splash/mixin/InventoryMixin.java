package net.fabricmc.splash.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.splash.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Quaternion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static java.awt.Color.HSBtoRGB;
import static net.minecraft.client.gui.screen.ingame.HandledScreen.BACKGROUND_TEXTURE;
import static net.minecraft.client.gui.screen.ingame.InventoryScreen.drawEntity;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.


@Mixin(InventoryScreen.class)
public abstract class InventoryMixin extends AbstractInventoryScreen{
    //MinecraftClient client;
    private float mouseX;
    private float mouseY;

    public InventoryMixin(PlayerEntity player) {
        super(player.playerScreenHandler, player.inventory, new TranslatableText("container.crafting"));
        this.passEvents = true;
        this.titleX = 97;
    }

    @Inject(at = @At("HEAD"), method = "drawBackground", cancellable = true)
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        final double millis = System.currentTimeMillis() % 10000L / 10000.0f;
        final int color = HSBtoRGB((float) millis, 1.0f, 1.0f); //0.8
        float r = (color >> 16 & 255) / 255.0f;
        float g = (color >> 8 & 255) / 255.0f;
        float b = (color & 255) / 255.0f;

        if(ModConfig.transparent==true) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.5F); //1.0F, 0.0F, 1.0F, 0.5F);
        } else {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
        this.client.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int i = this.x;
        int j = this.y;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        drawEntity(i + 51, j + 75, 30, (float)(i + 51) - this.mouseX, (float)(j + 75 - 50) - this.mouseY, this.client.player);
        ci.cancel();
    }
}