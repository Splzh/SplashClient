package net.fabricmc.splash.mixin; //ArmourHUDMixin

import javafx.scene.paint.Color;
import net.fabricmc.splash.ExampleMod;
import net.fabricmc.splash.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.MinecraftClientGame;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Vector;

import static net.fabricmc.splash.SplashMenu.rainbow;
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(InGameHud.class)
public class ArmourHUDMixin extends DrawableHelper {

    @Inject(at = @At("HEAD"), method = "render")
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        int height = MinecraftClient.getInstance().getWindow().getScaledHeight();
        int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
        //@ HELMET @
        if (ModConfig.enableArmourHUD == true) {
            if (MinecraftClient.getInstance().player.inventory.getArmorStack(3).isEmpty()) {
            } else {
                ItemStack helmet = MinecraftClient.getInstance().player.inventory.getArmorStack(3);
                itemRenderer.renderInGui(helmet, width - 25, 100);
                if(helmet.isDamageable()) {
                    int top = helmet.getDamage();
                    int bottom = helmet.getMaxDamage();
                    int percent = 100 - top * 100 / bottom;
                    drawCenteredString(matrices, textRenderer, Formatting.WHITE + String.valueOf(percent) + "%", width - 35, 103, 1);
                    if (percent < 15) {
                        drawCenteredString(matrices, textRenderer, Formatting.RED + "LOW", width - 55, 103, 1);
                    }
                }

            }
            //@ CHESTPLATE @
            if (MinecraftClient.getInstance().player.inventory.getArmorStack(2).isEmpty()) {

            } else {
                ItemStack chestplate = MinecraftClient.getInstance().player.inventory.getArmorStack(2);
                itemRenderer.renderInGui(chestplate, width - 25, 120);
                int top = chestplate.getDamage();
                int bottom = chestplate.getMaxDamage();
                int percent = 100 - top * 100 / bottom;
                drawCenteredString(matrices, textRenderer, Formatting.WHITE + String.valueOf(percent) + "%", width - 35, 123, 1);
                if (percent < 15) {
                    drawCenteredString(matrices, textRenderer, Formatting.RED + "LOW", width - 55, 123, 1);
                }
            }
            //@ LEGGINGS @
            if (MinecraftClient.getInstance().player.inventory.getArmorStack(1).isEmpty()) {
            } else {
                ItemStack leggings = MinecraftClient.getInstance().player.inventory.getArmorStack(1);
                itemRenderer.renderInGui(leggings, width - 25, 140);
                int top = leggings.getDamage();
                int bottom = leggings.getMaxDamage();
                int percent = 100 - top * 100 / bottom;
                drawCenteredString(matrices, textRenderer, Formatting.WHITE + String.valueOf(percent) + "%", width - 35, 143, 1);
                if (percent < 15) {
                    drawCenteredString(matrices, textRenderer, Formatting.RED + "LOW", width - 55, 143, 1);
                }
            }
            //@ BOOTS @
            if (MinecraftClient.getInstance().player.inventory.getArmorStack(0).isEmpty()) {
            } else {
                ItemStack boots = MinecraftClient.getInstance().player.inventory.getArmorStack(0);
                itemRenderer.renderInGui(boots, width - 25, 160);
                int top = boots.getDamage();
                int bottom = boots.getMaxDamage();
                int percent = 100 - top * 100 / bottom;
                drawCenteredString(matrices, textRenderer, Formatting.WHITE + String.valueOf(percent) + "%", width - 35, 163, 1);
                if (percent < 15) {
                    drawCenteredString(matrices, textRenderer, Formatting.RED + "LOW", width - 55, 163, 1);
                }
            }
            //@ MAIN HAND @
            if (MinecraftClient.getInstance().player.inventory.getMainHandStack().isEmpty()) {


            } else {
                ItemStack hand = MinecraftClient.getInstance().player.inventory.getMainHandStack();
                itemRenderer.renderInGui(hand, width - 25, 180);
                if(hand.isDamageable()) {
                    int top = hand.getDamage();
                    int bottom = hand.getMaxDamage();
                    int percent = 100 - top * 100 / bottom;
                    drawCenteredString(matrices, textRenderer, Formatting.WHITE + String.valueOf(percent) + "%", width - 35, 183, 1);
                    if (percent < 15) {
                        drawCenteredString(matrices, textRenderer, Formatting.RED + "LOW", width - 55, 183, 1);
                }
                }

            }
        }
    }
}