package net.fabricmc.splash.mixin;

import net.fabricmc.splash.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static java.awt.Color.HSBtoRGB;

@Mixin(EntityRenderDispatcher.class)
public class HitboxMixin {
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

    @Inject(method = "renderHitbox", at = @At("HEAD"), cancellable = true)
    private void renderHitbox(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, CallbackInfo ci) {
        if(ModConfig.enableHitbox == true) {
            float f = entity.getWidth() / 2.0F;
            this.drawBox(matrices, vertices, entity, 1.0F, 1.0F, 1.0F);
            if (entity instanceof LivingEntity || entity instanceof EndCrystalEntity) { //LivingEntity
                if (entity == MinecraftClient.getInstance().player) {

                } else {
                    final double millis = System.currentTimeMillis() % 10000L / 10000.0f;
                    final int color = HSBtoRGB((float) millis, 0.8f, 0.8f);
                    float r = (color >> 16 & 255) / 255.0f;
                    float g = (color >> 8 & 255) / 255.0f;
                    float b = (color & 255) / 255.0f;

                        float k = 0.01F;
                        if (entity instanceof EndCrystalEntity) {
                            WorldRenderer.drawBox(matrices, vertices, (double) (-f), (double) (0), (double) (-f), (double) f, (double) entity.getHeight(), (double) f, r, g, b, 1.0F);
                        } else {
                            WorldRenderer.drawBox(matrices, vertices, (double) (-f), (double) (0), (double) (-f), (double) f, (double) entity.getHeight(), (double) f, 1.0F, 1.0F, 1.0F, 1.0F);
                        }


                }

            }
            ci.cancel();

        }

            //Vec3d vec3d = entity.getRotationVec(tickDelta);
            Matrix4f matrix4f = matrices.peek().getModel();
            //vertices.vertex(matrix4f, 1.0F, entity.getStandingEyeHeight(), 1.0F).color(0, 0, 255, 255).next();
            //vertices.vertex(matrix4f, (float) (vec3d.x * 2.0D), (float) ((double) entity.getStandingEyeHeight() + vec3d.y * 2.0D), (float) (vec3d.z * 2.0D)).color(0, 0, 255, 255).next();
        }



    private void drawBox(MatrixStack matrix, VertexConsumer vertices, Entity entity, float red, float green, float blue) {
        Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());
        WorldRenderer.drawBox(matrix, vertices, box, red, green, blue, 1.0F);

    }
}