package net.fabricmc.splash.settings;

import net.fabricmc.splash.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import java.awt.*;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

public class HitboxSettings extends Screen {
    private final Screen parent;
    MinecraftClient MC;
    public HitboxSettings(Screen parent) {
        super(NarratorManager.EMPTY);
        this.parent = parent;
    }
    protected void init() {
        //LEFT
       /* addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100, 95, 20, new LiteralText("Pos: " + (ModConfig.armorposition ? Formatting.YELLOW+"RIGHT" : Formatting.GOLD+"LEFT")), widget -> {
            if (!ModConfig.armorposition) {
                ModConfig.armorposition = true;
            } else {
                ModConfig.armorposition = false;
            }
                widget.setMessage(new LiteralText("Pos: " + (ModConfig.armorposition ? Formatting.YELLOW+"RIGHT" : Formatting.GOLD+"LEFT")));
        }));
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100+25, 95, 20, new LiteralText("Armor HUD: " + (ModConfig.enableArmourHUD ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")), widget -> {
            if (!ModConfig.enableArmourHUD) {
                ModConfig.enableArmourHUD = true;
            } else {
                ModConfig.enableArmourHUD = false;
            }
            widget.setMessage(new LiteralText("Armor HUD: " + (ModConfig.enableArmourHUD ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
       }));
     */    }

        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredString(matrices, textRenderer, "Hitbox Settings", MinecraftClient.getInstance().currentScreen.width/2, 10, Color.WHITE.getRGB());
            drawCenteredString(matrices, textRenderer, "Work in progress, give me a break", MinecraftClient.getInstance().currentScreen.width/2, 80, Color.WHITE.getRGB());

            super.render(matrices, mouseX, mouseY, delta);
        }


        public boolean isPauseScreen() {
            return false;
        }


    }

