package net.fabricmc.splash;

import com.mojang.authlib.minecraft.MinecraftSessionService;
import javafx.stage.Stage;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.splash.discord.Client;
import net.fabricmc.splash.notifications.Notification;
import net.fabricmc.splash.notifications.NotificationManager;
import net.fabricmc.splash.notifications.NotificationType;
import net.fabricmc.splash.utils.CSBRenderer;
import net.fabricmc.splash.utils.FileManager;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.SmokerScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.options.StickyKeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.Session;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.gui.PlayerListGui;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

public class ExampleMod implements ModInitializer {
	HitResult blockHit;
	MinecraftClient client;
	@Override
	public void onInitialize() {
		FileManager.init();

			KeyBinding binding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding("Rainbow Chat", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, "Splash"));

			KeyBinding binding5 = KeyBindingHelper.registerKeyBinding(new KeyBinding("Splash Menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "Splash"));

		ClientTickCallback.EVENT.register(client -> {
				while (binding1.wasPressed()) {
					client.openScreen(new RainbowLine(client.currentScreen));

				}
				while (binding5.wasPressed()) {
					client.openScreen(new SMenu(client.currentScreen));
				}




			//	if (stickyBinding.isPressed()) {
			//		if (client.player.forwardSpeed > 0.1)
			//			client.player.setSprinting(true);

			//	}


			});
		}



	public static int namerainbow(int delay) {
		double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 60.0);
		rainbowState %= 360;
		return Color.getHSBColor((float) (rainbowState / 360.0f), 1.0f, 1.0f).getRGB(); //0.8f, 0.7f
	}
	public static int namerainbowdark(int delay) {
		double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 60.0);
		rainbowState %= 360;
		return Color.getHSBColor((float) (rainbowState / 360.0f), 0.8f, 0.7f).getRGB(); //0.8f, 0.7f
	}

}



