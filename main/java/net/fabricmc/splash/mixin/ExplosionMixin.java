package net.fabricmc.splash.mixin;

import net.fabricmc.splash.ModConfig;
import net.minecraft.client.particle.ExplosionEmitterParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(Explosion.class)
public abstract class ExplosionMixin
{
    @Redirect(method = "affectWorld",
            slice = @Slice(
                    from = @At("HEAD"),
                    to = @At(value = "FIELD",
                            target = "Lnet/minecraft/world/explosion/Explosion;affectedBlocks:Ljava/util/List;")),
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/particle/ParticleTypes;EXPLOSION_EMITTER:Lnet/minecraft/particle/DefaultParticleType;"))
    private DefaultParticleType redirectSpawnParticles() {

        {
            return ParticleTypes.ASH;
        }
    }
}