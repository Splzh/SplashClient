package net.fabricmc.splash.mixin;

import com.google.gson.annotations.Expose;
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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.math.RoundingMode;
import java.text.DecimalFormat;
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(InGameHud.class)
public class HealthMixin extends DrawableHelper {
    @Expose(serialize = false)
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    @Inject(at = @At("HEAD"), method = "render")
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        int height = MinecraftClient.getInstance().getWindow().getScaledHeight();
        int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
        double health = MinecraftClient.getInstance().player.getHealth();
        double maxhealth = MinecraftClient.getInstance().player.getMaxHealth();
        String healthcolor = "§f";
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);

        if(health<6) {
            healthcolor = "§c";
        }
        if(health>6) {
            healthcolor = "§f";
        }

        if (ModConfig.enableHealthInd == true) {
            drawCenteredString(matrices, textRenderer, healthcolor+df.format(health)+"/"+df.format(maxhealth), width/2, height/2+15, 1);
        }
    }
}