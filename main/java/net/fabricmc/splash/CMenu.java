package net.fabricmc.splash;

import net.fabricmc.splash.utils.SplashSlider;
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

public class CMenu extends Screen {
    private final Screen parent;
    public CMenu(Screen parent) {
        super(NarratorManager.EMPTY);
        this.parent = parent;
    }
    protected void init() {
        addButton(new SplashSlider(1, this.width - width+100, this.height/2 -50, 1));

        addButton(new ButtonWidget(this.width - width+100, this.height/2 -75, 95, 20, new LiteralText("Crystal: " + (ModConfig.enableHitbox ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")),widget -> {
            if (!ModConfig.enableHitbox) {
                ModConfig.enableHitbox = true;
            } else {
                ModConfig.enableHitbox = false;
            }
            widget.setMessage(new LiteralText("Crystal: " + (ModConfig.enableHitbox ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
        }));
        }
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredString(matrices, textRenderer,"Module Config", width/2, 10 + ModConfig.armour, Color.WHITE.getRGB());
            super.render(matrices, mouseX, mouseY, delta);
        }


        public boolean isPauseScreen() {
            return false;
        }


    }

