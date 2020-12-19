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
public class TotemMixin extends DrawableHelper {
    @Inject(at = @At("HEAD"), method = "render")
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        int height = MinecraftClient.getInstance().getWindow().getScaledHeight();
        int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int obby = -1;
        int totems = 0;
        String color = "§f";
        ItemStack offhandStack = MinecraftClient.getInstance().player.inventory.getStack(40);
        for (int i = 0; i < 50; i++) {
            if (MinecraftClient.getInstance().player.inventory.getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {
                obby = i;
                if (-1 < obby) {
                    totems++;
                }
            }

        }
        if(2>totems) {
            color = "§c";
        }
        if (ModConfig.enableTotemC == true) {
            itemRenderer.renderInGui(new ItemStack(Items.TOTEM_OF_UNDYING), 2, 90);
            drawCenteredString(matrices, textRenderer, color + String.valueOf(totems), 25, 95, 1);

        }
    }
    private boolean isTotem(ItemStack stack)
    {
        return stack.getItem() == Items.TOTEM_OF_UNDYING;
    }
}