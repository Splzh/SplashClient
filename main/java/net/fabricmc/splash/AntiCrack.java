package net.fabricmc.splash;

import net.fabricmc.splash.utils.ParticleUtil;
import net.minecraft.class_5489;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static net.fabricmc.splash.RainbowLine.rainbow;
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

public class AntiCrack extends Screen {
    private final Screen parent;
    final Identifier texture = new Identifier("modid", "weirdchamp.png");
    private class_5489 lines;


    public AntiCrack(Screen parent) {
        super(NarratorManager.EMPTY);
        this.lines = class_5489.field_26528;
        this.parent = parent;
    }

    protected void init() {
        super.init();
        int var10000 = this.lines.method_30887() + 1;
        this.textRenderer.getClass();
        int i = var10000 * 9;


    }

    private void onUpdate() {
    }



    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        fill(matrices, 0, 0, MinecraftClient.getInstance().currentScreen.width, MinecraftClient.getInstance().currentScreen.height,Color.BLACK.getRGB()); //-16777216
        drawCenteredString(matrices, textRenderer, Formatting.UNDERLINE+"Splash Anti-Crack/Blacklist", width/2, 50, Color.ORANGE.getRGB());
        drawCenteredString(matrices, textRenderer, "It appears that your account is either not registered to", width/2, 80, Color.WHITE.getRGB());
        drawCenteredString(matrices, textRenderer, "our whitelisted Minecraft accounts, or it might", width/2, 90, Color.WHITE.getRGB());
        drawCenteredString(matrices, textRenderer, "be Blacklisted. If you believe this is an error,", width/2, 100, Color.WHITE.getRGB());
        drawCenteredString(matrices, textRenderer, "contact "+Formatting.UNDERLINE+"Splash#7713"+Formatting.RESET+" on Discord.", width/2, 110, Color.WHITE.getRGB());
        MinecraftClient.getInstance().getTextureManager().bindTexture(texture);
        DrawableHelper.drawTexture(matrices, width / 2 - 25, height / 2 + 15, 0, 0, 50, 50, 50, 50);
    }

}
