package net.fabricmc.splash.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Mixin(Item.class)
public class ItemMixin {


    @Inject(at = @At("HEAD"), method = "appendTooltip")
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext, CallbackInfo info) {
            tooltipContext.isAdvanced();
            if(itemStack.isDamageable()) {
                tooltip.add(new TranslatableText(Formatting.WHITE + "Durability: "+(itemStack.getMaxDamage() - itemStack.getDamage() + " / " + itemStack.getMaxDamage())));
            }
    }


}
