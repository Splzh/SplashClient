//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.fabricmc.splash;

import javafx.application.Application;
import javafx.application.Platform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_5489;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.CommandSuggestor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import sun.misc.JavaAWTAccess;

import javax.swing.*;
import java.awt.HeadlessException;
import java.awt.*;
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Environment(EnvType.CLIENT)
public class SplashMenu extends Screen {
    private final Screen parent;
    protected TextFieldWidget chatField;
    private int messageHistorySize = -1;
    private String originalChatText = "";


    public SplashMenu(Screen parent) {
        super(NarratorManager.EMPTY);
        this.parent = parent;
    }

    public static boolean enabled = false;

    public void init() {
        this.addButton(new ButtonWidget(this.width / 2 - 49 -50, height / 2 + 58, 98, 20, new TranslatableText("Test button"), (Widget) -> {

        }));


        //this.client.keyboard.enableRepeatEvents(true);
        super.init();
        //int var10000 = this.lines.method_30887() + 1;
        this.textRenderer.getClass();
        this.messageHistorySize = this.client.inGameHud.getChatHud().getMessageHistory().size();
        this.chatField = new TextFieldWidget(this.textRenderer, this.width/2-47, this.height/2-20, this.width - 16, 12, new TranslatableText("chat.editBox")) {  //chat.editBox

        };
        this.chatField.setMaxLength(16);
        this.chatField.setHasBorder(false);
        this.chatField.setText(this.originalChatText);
        this.chatField.setChangedListener(this::onChatFieldUpdate);
        this.children.add(this.chatField);
        this.setInitialFocus(this.chatField);
    }
    private void onChatFieldUpdate(String chatText) {
        String string = this.chatField.getText();
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {


        String string = this.chatField.getText();
        fillGradient(matrices, 0, 0, width, height, -16777216, 0);
        // OOO
        drawCenteredString(matrices, this.textRenderer, Formatting.RED+"Currently Unavailable", this.width/2, this.height/2-60, 16777215);
        // OOO
        drawCenteredString(matrices, this.textRenderer, Formatting.BOLD+"Leaked Alt Account Library", this.width/2, this.height/2-50, 16777215);
        drawCenteredString(matrices, this.textRenderer, Formatting.GRAY+"Search suspicious accounts to find an account owner.", this.width/2, this.height/2-40, 16777215);
        fill(matrices, this.width/2+51, this.height/2-9, this.width/2-51, this.height/2-24, this.client.options.getTextBackgroundColor(rainbow(0*300)));
        fill(matrices, this.width/2+50, this.height/2-10, this.width/2-50, this.height/2-23, this.client.options.getTextBackgroundColor(-16777216));
        if(string.equals("")){

        } else {
            drawCenteredString(matrices, this.textRenderer, Formatting.BOLD+"Account owner:", this.width /2, this.height/2+0, 16777215);
            if(string.contains("Splzh") || string.contains("PurityBot") || string.contains("RocketArena")) {
                drawCenteredString(matrices, this.textRenderer, Formatting.GRAY+"Splash", this.width /2, this.height/2+10, 16777215);
            } else {
                drawCenteredString(matrices, this.textRenderer, Formatting.GRAY+"Nothing found.", this.width /2, this.height/2+10, 16777215);
            }
        }


        if(enabled == true) {
            renderTooltip(matrices, Text.of("Test ToolTip"), mouseX, mouseY); //this.width / 2 - 49 -50, height / 2 - 58
        }
        this.chatField.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }
    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 1.0f, 1.0f).getRGB(); //0.8f, 0.7f
    }
}

