package net.fabricmc.splash.mixin;

import net.fabricmc.splash.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.MinecraftClientGame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(PotionItem.class)
public class PotionItemMixin extends Item {

    public PotionItemMixin(Settings settings) {
        super(settings);
    }

    @Overwrite
    public boolean hasGlint(ItemStack stack) {
        if(ModConfig.enableGlint == true){
            return false;
        }
        return super.hasGlint(stack) || !PotionUtil.getPotionEffects(stack).isEmpty();
    }

}