package net.fabricmc.splash.mixin;

import com.sun.media.jfxmedia.events.PlayerEvent;
import net.fabricmc.splash.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.texture.StatusEffectSpriteManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.Objects;

import static net.fabricmc.splash.ExampleMod.namerainbow;
import static net.fabricmc.splash.ExampleMod.namerainbowdark;
import static net.fabricmc.splash.RainbowLine.rainbow;
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(EntityRenderer.class)
public class NametagMixin {
    @Final
    @Shadow
    protected EntityRenderDispatcher dispatcher;


    //AbstractClientPlayerEntity abstractClientPlayerEntity;

    //THIS CURRENTLY NEEDS A RE-WRITE

    @Inject(method = "render", at = @At("HEAD"))
    private void render(Entity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,CallbackInfo ci) {


        int index = 0;
        Text text = Text.of("");
        int color = -1;
        int backcolor = 0;
        double d = this.dispatcher.getSquaredDistanceToCamera(entity);
        text = Text.of("");
        String PVP = entity.getName().toString();

        if (entity.getName().toString().contains("Splzh")) {
            text = Text.of("Splash Dev");
            color = namerainbow(1*300);;
        }
        if (entity.getName().toString().contains("Spzh")) {
            text = Text.of("Splash Dev");
            color = namerainbow(2*300);;
        }
        if (entity.getName().toString().contains("PurityBot")) {
            text = Text.of(".dupe hand");
            color = namerainbow(3*300);;
        }
        if (entity.getName().toString().contains("GamingPartridge")) {
            text = Text.of("Mapart Man");
            color = namerainbow(0*300);
        }
        if (entity.getName().toString().contains("losttrafficcone")) {
            text = Text.of("PaCo Leader");
            color = namerainbow(0*300);
        }
        if (entity.getName().toString().contains("Syuu_Inri")) {
            text = Text.of("❤ Xoxo");
            color = Color.PINK.getRGB();
        }
        if (entity.getName().toString().contains("lolingcraft")) {
            text = Text.of("#1 nazi");
            color = 0x00ff04;
        }
        if (entity.getName().toString().contains("Lacksal")) {
            text = Text.of("1000 ping madlad");
            color = namerainbow(0 * 300);
        }
        if (entity.getName().toString().contains("Jimothy_12")) {
            text = Text.of("Corrupt Staff");
            color = 0x510466;
        }

        if (entity.getName().toString().contains("L_Cancel")) {
            text = Text.of("Texture Funnyman");
            color = 0xba261c;
        }

        if (entity.getName().toString().contains(MinecraftClient.getInstance().player.getName().toString())) {
            text = Text.of("");
        }
        if (entity.isInvisible()) {
            text = Text.of("");
        }


        @SuppressWarnings("rawtypes") EntityRenderer entityRenderer = (EntityRenderer) (Object) this;
        if (d <= 4096.0D) {

            boolean bl = !entity.isSneaky();
            float f = entity.getHeight() + 0.5F;
            int i = 10 + (10 * index);
            matrices.push();
            matrices.translate(0.0D, f, 0.0D);
            matrices.multiply(this.dispatcher.getRotation());
            matrices.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = matrices.peek().getModel();
            float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
            int j = (int) (g * 255.0F) << 24;
            TextRenderer textRenderer = entityRenderer.getFontRenderer();
            float h = (float) (-textRenderer.getWidth(text) / 2);

            if (bl) {
                    textRenderer.draw(text, h, -(float) i, color, false, matrix4f, vertexConsumers, false, backcolor, light);

                }
                matrices.pop();
                index++;
            }
        }
    }
