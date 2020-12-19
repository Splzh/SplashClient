package net.fabricmc.splash.settings;

import net.fabricmc.splash.CMenu;
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

public class Settings extends Screen {
    private final Screen parent;
    MinecraftClient MC;
    public Settings(Screen parent) {
        super(NarratorManager.EMPTY);
        this.parent = parent;
    }
    protected void init() {
        //LEFT
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100, 95, 20, new LiteralText("Time HUD"), widget -> {
                //TIME HUD MENU
        }));
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100+25, 95, 20, new LiteralText("Armor HUD"), widget -> {
            //ARMOR HUD MENU
        }));
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100+25+25, 95, 20, new LiteralText("Player HUD"), widget -> {
            //PLAYER HUD MENU
        }));
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100+25+25+25, 95, 20, new LiteralText("Health HUD"), widget -> {
        //HEALTH HUD MENU
        }));
        addButton(new ButtonWidget(this.width / 2 - 100, this.height/2 -100+25+25+25+25, 95, 20, new LiteralText(Formatting.GRAY+"Totem HUD"), widget -> {
        //TOTEM HUD MENU
        }));

        //RIGHT
        addButton(new ButtonWidget(this.width / 2 + 5, this.height/2 -100, 95, 20, new LiteralText(Formatting.GRAY+"No Fire"),widget -> {
        //NO FIRE MENU
        }));
        addButton(new ButtonWidget(this.width / 2 + 5, this.height/2 -100+25, 95, 20, new LiteralText(Formatting.GRAY+"NoHurtCam"),widget -> {

        }));
        addButton(new ButtonWidget(this.width / 2 + 5, this.height/2 -100+25+25, 95, 20, new LiteralText(Formatting.GRAY+"Potion Glint"),widget -> {
        }));
        addButton(new ButtonWidget(this.width / 2 + 5, this.height/2 -100+25+25+25, 95, 20, new LiteralText("HitboxPlus"),widget -> {
            client.openScreen(new HitboxSettings(client.currentScreen));
        }));


        }
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredString(matrices, textRenderer, "Splash Settings", MinecraftClient.getInstance().currentScreen.width/2, 10, Color.WHITE.getRGB());
            super.render(matrices, mouseX, mouseY, delta);
        }


        public boolean isPauseScreen() {
            return false;
        }


    }

