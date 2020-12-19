package net.fabricmc.splash.notifications;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

public class NotificationManager {
    private static LinkedBlockingQueue<Notification> pendingNotifications = new LinkedBlockingQueue<>();
    private static Notification currentNotification = null;

    public static void show(Notification notification) {
        pendingNotifications.add(notification);
    }

    public static void update() {
        if (currentNotification != null && !currentNotification.isShown()) {
            currentNotification = null;
        }

        if (currentNotification == null && !pendingNotifications.isEmpty()) {
            currentNotification = pendingNotifications.poll();
            currentNotification.show();
        }

    }

    public static void render(MatrixStack matrices, TextRenderer textrenderer) {
        update();

        if (currentNotification != null)
            currentNotification.render(matrices, textrenderer);
    }
}
