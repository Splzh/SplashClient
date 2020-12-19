package net.fabricmc.splash;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.splash.ModConfig;
import net.fabricmc.splash.settings.Settings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.awt.*;
import java.util.Optional;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

public class SMenu extends Screen {
    private final Screen parent;
    MinecraftClient MC;
    public SMenu(Screen parent) {
        super(NarratorManager.EMPTY);
        this.parent = parent;
    }
    protected void init() {
        //LEFT
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100, 95, 20, new LiteralText("Time HUD: " + (ModConfig.enableTime ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")), widget -> {
                if (!ModConfig.enableTime) {
                    ModConfig.enableTime = true;
                } else {
                    ModConfig.enableTime = false;
                }
                widget.setMessage(new LiteralText("Time HUD: " + (ModConfig.enableTime ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
        }));
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100+25, 95, 20, new LiteralText("Armor HUD: " + (ModConfig.enableArmourHUD ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")), widget -> {
            if (!ModConfig.enableArmourHUD) {
                ModConfig.enableArmourHUD = true;
            } else {
                ModConfig.enableArmourHUD = false;
            }
            widget.setMessage(new LiteralText("Armor HUD: " + (ModConfig.enableArmourHUD ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
        }));
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100+25+25, 95, 20, new LiteralText("Player HUD: " + (ModConfig.enablePlayRen ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")), widget -> {
            if (!ModConfig.enablePlayRen) {
                ModConfig.enablePlayRen = true;
            } else {
                ModConfig.enablePlayRen = false;
            }
            widget.setMessage(new LiteralText("Player HUD: " + (ModConfig.enablePlayRen ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
        }));
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100+25+25+25, 95, 20, new LiteralText("Health HUD: " + (ModConfig.enableHealthInd ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")), widget -> {
            if (!ModConfig.enableHealthInd) {
                ModConfig.enableHealthInd = true;
            } else {
                ModConfig.enableHealthInd = false;
            }
            widget.setMessage(new LiteralText("Health HUD: " + (ModConfig.enableHealthInd ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
        }));

        //RIGHT
        addButton(new ButtonWidget(this.width / 2 + 5, this.height/2 -100, 95, 20, new LiteralText("No Fire: " + (ModConfig.enableNoFire ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")),widget -> {
            if (!ModConfig.enableNoFire) {
                ModConfig.enableNoFire = true;
            } else {
                ModConfig.enableNoFire = false;
            }
            widget.setMessage(new LiteralText("No Fire: " + (ModConfig.enableNoFire ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
        }));
        addButton(new ButtonWidget(this.width / 2 + 5, this.height/2 -100+25, 95, 20, new LiteralText("Totem HUD: " + (ModConfig.enableTotemC ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")), widget -> {
            if (!ModConfig.enableTotemC) {
                ModConfig.enableTotemC = true;
            } else {
                ModConfig.enableTotemC = false;
            }
            widget.setMessage(new LiteralText("Totem HUD: " + (ModConfig.enableTotemC ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
        }));
        addButton(new ButtonWidget(this.width / 2 + 5, this.height/2 -100+25+25, 95, 20, new LiteralText("Potion Glint: " + (ModConfig.enableGlint ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")),widget -> {
            if (!ModConfig.enableGlint) {
                ModConfig.enableGlint = true;
            } else {
                ModConfig.enableGlint = false;
            }
            widget.setMessage(new LiteralText("Potion Glint: " + (ModConfig.enableGlint ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
        }));
        addButton(new ButtonWidget(this.width / 2 + 5, this.height/2 -100+25+25+25, 95, 20, new LiteralText("HitboxPlus: " + (ModConfig.enableHitbox ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")),widget -> {
            if (!ModConfig.enableHitbox) {
                ModConfig.enableHitbox = true;
            } else {
                ModConfig.enableHitbox = false;
            }
            widget.setMessage(new LiteralText("HitboxPlus: " + (ModConfig.enableHitbox ? Formatting.GREEN+"ON" : Formatting.RED+"OFF")));
        }));

        //Module Config
        //addButton(new ButtonWidget(this.width-105, this.height-30, 95, 20, new LiteralText("Settings"),widget -> {
       //     client.openScreen(new Settings(client.currentScreen));
      //  }));
        }
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredString(matrices, textRenderer, "Splash Client", MinecraftClient.getInstance().currentScreen.width/2, 10, Color.WHITE.getRGB());
            super.render(matrices, mouseX, mouseY, delta);
        }


        public boolean isPauseScreen() {
            return false;
        }


    }

