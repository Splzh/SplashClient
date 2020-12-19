package net.fabricmc.splash;

import com.sun.javafx.scene.control.behavior.ButtonBehavior;
import javafx.scene.input.MouseButton;
import net.fabricmc.splash.utils.ParticleUtil;
import net.fabricmc.splash.utils.SplashSlider;
import net.minecraft.block.Material;
import net.minecraft.class_5489;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.options.Option;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static net.fabricmc.splash.RainbowLine.rainbow;
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

public class SplashMenuTwo extends Screen {
    private final Screen parent;
    private static final Text header;
    private static final Text message;
    private static final Text Render;
    private ParticleUtil particles;
    public static boolean isSplashOpen = false;





    private static final Text proceedText;
    private CheckboxWidget checkbox;
    private class_5489 lines;


    public SplashMenuTwo(Screen parent) {
        super(NarratorManager.EMPTY);
        this.lines = class_5489.field_26528;
        this.parent = parent;
    }

    protected void init() {
        super.init();
        int var10000 = this.lines.method_30887() + 1;
        this.textRenderer.getClass();
        int i = var10000 * 9;



        //## COLOURED BUTTONS ##


        //Button width will be 'width/2 -49' to get to the exact middle.

        String Timeenabled = "-";
        if(true) {
            isSplashOpen = true;
        } else {
            isSplashOpen = false;
        }

        this.addButton(new ButtonWidget(this.width / 2 - 49 -50, height / 2 - 58, 98, 20, new TranslatableText("Time HUD"), (timeWidget) -> {

            if (ModConfig.enableTime == false) {
                ModConfig.enableTime = true;


            } else {
                    ModConfig.enableTime = false;

            }
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 49 +50, height / 2 - 58, 98, 20, new TranslatableText("Player HUD"), (playrenWidget) -> {
            if (ModConfig.enablePlayRen == false) {
                ModConfig.enablePlayRen = true;
            } else {
                ModConfig.enablePlayRen = false;
            }
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 49 -50, height / 2 - 36, 98, 20, new TranslatableText("No Fire"), (fireWidget) -> {
            if (ModConfig.enableNoFire == false) {
                ModConfig.enableNoFire = true;
            } else {
                ModConfig.enableNoFire = false;
            }
        }));

        this.addButton(new ButtonWidget(this.width / 2 - 49 +50, height / 2 - 36, 98, 20, new TranslatableText("NoHurtCam"), (nohurtWidget) -> {
            if (ModConfig.enableNoHurt == false) {
                ModConfig.enableNoHurt = true;
            } else {
                ModConfig.enableNoHurt = false;
            }
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 49 -50, height / 2 - 14, 98, 20, new TranslatableText("Armour HUD"), (armhbWidget) -> {
            if (ModConfig.enableArmourHUD == false) {
                ModConfig.enableArmourHUD = true;
            } else {
                ModConfig.enableArmourHUD = false;
            }
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 49 +50, height / 2 - 14, 98, 20, new TranslatableText("Potion Glint"), (potgWidget) -> {
            if (ModConfig.enableGlint == false) {
                ModConfig.enableGlint = true;
            } else {
                ModConfig.enableGlint = false;
            }
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 49 -50, height / 2 + 8, 98, 20, new TranslatableText("Health HUD"), (heliWidget) -> {
            if (ModConfig.enableHealthInd == false) {
                ModConfig.enableHealthInd = true;
            } else {
                ModConfig.enableHealthInd = false;
            }
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 49 +50, height / 2 + 8, 98, 20, new TranslatableText("L.A.A.L."), (heliWidget) -> {
            client.openScreen(new SplashMenu(client.currentScreen));
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 49 -50, height / 2 + 30, 98, 20, new TranslatableText("Totem Counter"), (heliWidget) -> {
            if (ModConfig.enableTotemC == false) {
                ModConfig.enableTotemC = true;
            } else {
                ModConfig.enableTotemC = false;
            }
        }));

       /* this.addButton(new ButtonWidget(this.width / 2 - 49, height / 2, 20, 20, new TranslatableText("Settings"), (armoptWidget) -> {
            if (ModConfig.enableHealthInd == false) {
                ModConfig.enableHealthInd = true;
            } else {
                ModConfig.enableHealthInd = false;
            }
        })); */
    }

    private void onUpdate() {
    }



    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        fill(matrices, width / 2 + 101, height / 2 + 101, width / 2 - 100, height / 2 - 100, -16777216);
        fill(matrices, width / 2 + 100, height / 2 + 100, width / 2 - 101, height / 2 - 101, -429299350);
        fill(matrices, width / 2 + 100, height / 2 + 100, width / 2 - 100, height / 2 - 100, -11184810);
        fill(matrices, width / 2 + 99, height / 2 - 99, width / 2 - 99, height / 2 - 88, -429299350);
        final Identifier backtexture = new Identifier("modid", "gradient2.jpg");
        final Identifier ticktexture = new Identifier("modid", "tick.png");
        GL11.glColor4f(1, 1, 1, 5);
        GL11.glEnable(GL11.GL_BLEND);
        MinecraftClient.getInstance().getTextureManager().bindTexture(backtexture);
        DrawableHelper.drawTexture(matrices, width / 2 - 100, height / 2 - 100, 0, 0, 200, 200, 300, 200);
        if (ModConfig.enableRainbow == true) {
            fill(matrices, width / 2 + 99, height / 2 - 99, width / 2 - 99, height / 2 - 88, rainbow(0 * 300)); //Blue //987911991
        }
        fill(matrices, width / 2 + 89, height / 2 - 98, width / 2 + 98, height / 2 - 89, -11184810); //Dark Grey
        fill(matrices, width / 2 + 90, height / 2 - 97, width / 2 + 97, height / 2 - 90, -429299350); //Light Grey
        drawTextWithShadow(matrices, textRenderer, Render, width / 2 - 98, height / 2 - 98, -1);
        //## LOGO ##
        final Identifier texture = new Identifier("modid", "modules.png");
        GL11.glColor4f(1, 1, 1, 5);
        GL11.glEnable(GL11.GL_BLEND);
        MinecraftClient.getInstance().getTextureManager().bindTexture(texture);
        DrawableHelper.drawTexture(matrices, width / 2 - 25, height / 2 - 100, 0, 0, 50, 50, 50, 50);


        this.lines.method_30888(matrices, this.width / 2, 70);
        super.render(matrices, mouseX, mouseY, delta);
        //## BUTTON OVERLAY ##
        if (ModConfig.enableTime == true) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(ticktexture);
            DrawableHelper.drawTexture(matrices, this.width / 2 - 49 + 35, height / 2 - 54, 0, 0, 10, 10, 10, 10);
        }
        if (ModConfig.enableNoFire == true) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(ticktexture);
            DrawableHelper.drawTexture(matrices, this.width / 2 - 49 + 35, height / 2 - 32, 0, 0, 10, 10, 10, 10);
        }
        if (ModConfig.enablePlayRen == true) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(ticktexture);
            DrawableHelper.drawTexture(matrices, this.width / 2 - 49 + 135, height / 2 - 54, 0, 0, 10, 10, 10, 10);
        }
        if (ModConfig.enableNoHurt == true) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(ticktexture);
            DrawableHelper.drawTexture(matrices, this.width / 2 - 49 + 135, height / 2 - 32, 0, 0, 10, 10, 10, 10);
        }
        if (ModConfig.enableArmourHUD == true) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(ticktexture);
            DrawableHelper.drawTexture(matrices, this.width / 2 - 49 + 35, height / 2 - 10, 0, 0, 10, 10, 10, 10);
        }
        if (ModConfig.enableGlint == true) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(ticktexture);
            DrawableHelper.drawTexture(matrices, this.width / 2 - 49 + 135, height / 2 - 10, 0, 0, 10, 10, 10, 10);
        }
        if (ModConfig.enableHealthInd == true) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(ticktexture);
            DrawableHelper.drawTexture(matrices, this.width / 2 - 49 + 35, height / 2 + 12, 0, 0, 10, 10, 10, 10);
        }
        if (ModConfig.enableTotemC == true) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(ticktexture);
            DrawableHelper.drawTexture(matrices, this.width / 2 - 49 + 35, height / 2 + 34, 0, 0, 10, 10, 10, 10);
        }

        //fill(matrices, TopBoxLeft, TopBoxRight, BottomBoxLeft, BottomBoxRight, 0x0);
        if ( (mouseX > this.width / 2 - 49 -50) && (mouseX <= this.width / 2 - 49 +50) && (mouseY > height / 2 + 58)  && (mouseY <= height / 2 - 58) ) {
            renderTooltip(matrices, Text.of("Fuck this"), mouseX,mouseY);

        }

       /* if(mouseX >= this.width / 2 - 49 + 35 && mouseX <= this.width / 2 - 49 + 35 + height / 2 - 54 &&
                mouseY >= this.width / 2 - 49 + 35){
            renderTooltip(matrices, Text.of("Displays a real-time clock in the corner of the screen."), mouseX,mouseY); //this.width / 2 - 49 -50, height / 2 - 58
        } else {
        }*/


    }




    static {
        header = (new TranslatableText("multiplayerWarning.header")).formatted(Formatting.BOLD);
        message = new TranslatableText("Splash Client");
        Render = new TranslatableText("Splzh");



        proceedText = header.shallowCopy().append("\n").append(message);
    }
    public static boolean isSplash() {
        return true;
    }

}
