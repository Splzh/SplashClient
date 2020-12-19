package net.fabricmc.splash.mixin;

import net.fabricmc.splash.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sun.awt.image.PixelConverter;

import java.awt.*;

import static java.awt.Color.HSBtoRGB;
import static net.fabricmc.splash.SplashMenu.rainbow;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(InGameHud.class)
public class TitleMixin extends DrawableHelper {
    @Inject(at = @At("HEAD"), method = "render")
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        Color BACKGROUND = new Color(0, 0, 0, 125);
        final Identifier texture = new Identifier("modid", "splashlogo.png"); //splash.png
        final double millis = System.currentTimeMillis() % 10000L / 10000.0f;
        final int color = HSBtoRGB((float) millis, 1.0f, 1.0f); //0.8
        float r = (color >> 16 & 255) / 255.0f;
        float g = (color >> 8 & 255) / 255.0f;
        float b = (color & 255) / 255.0f;
        int height = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2;
        int width = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        GL11.glColor4f(r, g, b, 1);
        GL11.glEnable(GL11.GL_BLEND);
        fill(matrices, 0, 0, 55, 30, BACKGROUND.getRGB());
        MinecraftClient.getInstance().getTextureManager().bindTexture(texture);
        DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, 20, 20, 20, 20); //-15
        drawStringWithShadow(matrices, textRenderer, "Splash", 20, 2, -1);
        drawStringWithShadow(matrices, textRenderer, "v2.3", 20, 11, -1); //§7
        drawStringWithShadow(matrices, textRenderer, "§f" + MinecraftClient.getInstance().fpsDebugString.split("f")[0] + "fps", 1, 20, -1);
        }
    }




