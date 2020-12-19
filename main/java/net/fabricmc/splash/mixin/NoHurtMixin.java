package net.fabricmc.splash.mixin;

import net.fabricmc.splash.ModConfig;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(GameRenderer.class)
public class NoHurtMixin {
    @Inject(at = {@At("HEAD")},
            method = {
                    "bobViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V"},
            cancellable = true)
    public void onBobViewWhenHurt(MatrixStack matrixStack, float f, CallbackInfo ci) {
        if(ModConfig.enableNoHurt == true)
            ci.cancel();
    }
}
