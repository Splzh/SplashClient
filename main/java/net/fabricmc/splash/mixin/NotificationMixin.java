package net.fabricmc.splash.mixin;

import com.google.common.collect.Ordering;
import com.mojang.blaze3d.systems.RenderSystem;
import javafx.event.ActionEvent;
import net.fabricmc.splash.ExampleMod;
import net.fabricmc.splash.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(InGameHud.class)
public class NotificationMixin extends DrawableHelper {
    @Inject(at = @At("HEAD"), method = "render")
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        int height = MinecraftClient.getInstance().getWindow().getScaledHeight();
        int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        int players = MinecraftClient.getInstance().getGame().getCurrentSession().getPlayerCount();
        Collection<String> players2 = Collections.singleton(MinecraftClient.getInstance().player.getScoreboard().getTeamNames().toString());

        renderPlayer();
        //## TIME ##
        LocalDateTime Time = LocalDateTime.now();



        DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        String dateString = dateFormat.format(new Date()).toString();
        if (ModConfig.enableTime == true) {
            drawStringWithShadow(matrices, textRenderer, "§f" + dateString, MinecraftClient.getInstance().getWindow().getScaledWidth() - 45, 1, 1);
        } else {
        }
        if(!MinecraftClient.getInstance().isInSingleplayer()){
            if (!MinecraftClient.getInstance().getCurrentServerEntry().label.equals(null)) {
                drawCenteredString(matrices, textRenderer, MinecraftClient.getInstance().getCurrentServerEntry().label.asString(), MinecraftClient.getInstance().getWindow().getScaledWidth() / 2, MinecraftClient.getInstance().getWindow().getScaledHeight() - 70, -1);
            }
    }
        //## FIRE RENDER ##
        if (ModConfig.enableNoFire == true) {
            if (MinecraftClient.getInstance().player.isOnFire() == true) {
                drawCenteredString(matrices, textRenderer, Formatting.BOLD + "You are on fire!", MinecraftClient.getInstance().getWindow().getScaledWidth() / 2, MinecraftClient.getInstance().getWindow().getScaledHeight() - 70, -1);
            }
        }

    }



    //## PLAYER RENDERER ##
    private void renderPlayer() {
        if (ModConfig.enablePlayRen == true) {
            InventoryScreen.drawEntity(15, 80, 25, -10, 0, MinecraftClient.getInstance().player); //25
        } else {
        }

    }
}