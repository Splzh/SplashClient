package net.fabricmc.splash.notifications;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import java.awt.Color;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

public class Notification extends Screen {
    private NotificationType type;
    private String title;
    private String messsage;
    int height2 = MinecraftClient.getInstance().getWindow().getScaledHeight();
    int width2 = MinecraftClient.getInstance().getWindow().getScaledWidth();

    private long start;

    private long fadedIn;
    private long fadeOut;
    private long end;


    public Notification(NotificationType type, String title, String messsage, int length) {
        super(Text.of(title));
        this.type = type;
        this.title = title;
        this.messsage = messsage;

        fadedIn = 200 * length;
        fadeOut = fadedIn + 500 * length;
        end = fadeOut + fadedIn;
    }

    public void show() {
        start = System.currentTimeMillis();
    }

    public boolean isShown() {
        return getTime() <= end;
    }

    private long getTime() {
        return System.currentTimeMillis() - start;
    }

    public void render(MatrixStack matrices, TextRenderer textRenderer) {
        double offset = 0; //0
        int width = width2; //120
        int height = height2; //30
        long time = getTime();

        if (time < fadedIn) {
            offset = Math.tanh(time / (double) (fadedIn) * 3.0) * width2-width2+100;
        } else if (time > fadeOut) {
            offset = (Math.tanh(3.0 - (time - fadeOut) / (double) (end - fadeOut) * 3.0) * width2-width2+100); //width-300
        } else {
            offset = width2-width2+100;
        }

        Color color = new Color(0, 0, 0, 220);
        Color color1;

        if (type == NotificationType.INFO)
            color1 = new Color(0, 26, 169);
        else if (type == NotificationType.WARNING)
            color1 = new Color(245, 59, 2);
        else {
            color1 = new Color(204, 0, 18);
            int i = Math.max(0, Math.min(255, (int) (Math.sin(time / 100.0) * 255.0 / 2 + 127.5)));
            color = new Color(i, 0, 0, 220);
        }

        fill(matrices, (int)(width - offset), height-30, width, height-5, color.getRGB()); //height-5-height
        fill(matrices, (int)(width - offset), height-30, (int) (width-offset+4), height-5, color1.getRGB());
        drawTextWithShadow(matrices, textRenderer, Text.of(title), (int) (width+5-offset), height-28, -1);
        drawTextWithShadow(matrices, textRenderer, Text.of(messsage), (int) (width+5-offset), height-15, -1);
    }
}
