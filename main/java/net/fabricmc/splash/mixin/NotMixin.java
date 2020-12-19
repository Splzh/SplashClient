package net.fabricmc.splash.mixin;

import net.fabricmc.splash.notifications.Notification;
import net.fabricmc.splash.notifications.NotificationManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class NotMixin {
TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
    @Inject(at = @At("HEAD"), method = "render")
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        NotificationManager.render(matrices, textRenderer);
    }
}
