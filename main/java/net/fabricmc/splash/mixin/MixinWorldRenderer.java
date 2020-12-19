package net.fabricmc.splash.mixin;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.scenario.effect.impl.Renderer;
import net.fabricmc.splash.ModConfig;
import net.fabricmc.splash.utils.CSBDefaultRenderer;
import net.fabricmc.splash.utils.CSBInfo;
import net.fabricmc.splash.utils.CSBRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import java.awt.*;
import java.util.Objects;

//import static net.fabricmc.splash.ExampleMod.RENDERERS;
import static net.fabricmc.splash.utils.CSB.HSBtoRGB;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.


@SuppressWarnings("unused")
@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer implements CSBInfo {
    final double millis = System.currentTimeMillis() % 10000L / 10000.0f;
    final int color = Color.HSBtoRGB((float) millis, 0.8f, 0.8f);
    float R = (color >> 16 & 255) / 255.0f;
    float G = (color >> 8 & 255) / 255.0f;
    float B = (color & 255) / 255.0f;
    //@Unique private boolean render = true;
    @Unique private float r = 1f;
    @Unique private float g = 1f;
    @Unique private float b = 1f;
    @Unique private float a = 1f;
    @Unique private float blinkingAlpha = 0.5f;
    @Shadow private ClientWorld world;

    @Shadow
    private static void drawShapeOutline(MatrixStack matrixStack, VertexConsumer vertexConsumer, VoxelShape voxelShape, double d, double e, double f, float g, float h, float i, float j) {
    }


    @Shadow @Final private MinecraftClient client;


        @Redirect(method = "render", at = @At(value = "INVOKE",
                target = "Lnet/minecraft/client/render/WorldRenderer;drawBlockOutline(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/entity/Entity;DDDLnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V",
                ordinal = 0))

        private void onDrawShapeOutline (WorldRenderer worldRenderer, MatrixStack matrixStack, VertexConsumer
        vertexConsumer, Entity entity,double d, double e, double f, BlockPos blockPos, BlockState blockState){
                drawShapeOutline(matrixStack, vertexConsumer, blockState.getOutlineShape(world, blockPos, ShapeContext.of(entity)), blockPos.getX() - d, blockPos.getY() - e, blockPos.getZ() - f, 1, 0.5F, 0, 0.8F); //1.0F, 1.0F, 1.0F, 0.8F
                return;
    }


        @Inject(method = "render",
                at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;renderWorldBorder(Lnet/minecraft/client/render/Camera;)V",
                        shift = At.Shift.AFTER))
        private void renderWorldBorder (MatrixStack matrices,float delta, long limitTime,
        boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager
        lightmapTextureManager, Matrix4f matrix4f, CallbackInfo ci){
        r = 1.0F;
        g = 1.0F;
        b = 1.0F;
        a = 1.0F;
    }


    @Override
    public float getOutlineRed() {
        return r;
    }

    @Override
    public float getOutlineGreen() {
        return g;
    }

    @Override
    public float getOutlineBlue() {
        return b;
    }

    @Override
    public float getOutlineAlpha() {
        return a;
    }

    @Override
    public float getInnerRed() {
        return r;
    }

    @Override
    public float getInnerGreen() {
        return g;
    }

    @Override
    public float getInnerBlue() {
        return b;
    }

    @Override
    public float getInnerAlpha() {
        return blinkingAlpha;
    }
}