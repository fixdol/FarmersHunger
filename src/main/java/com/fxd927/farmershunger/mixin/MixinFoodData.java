package com.fxd927.farmershunger.mixin;

import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class MixinFoodData {
    @Shadow
    private int foodLevel;

    @Shadow
    private float saturationLevel;

    @Inject(method = "eat*", at = @At("HEAD"), cancellable = true)
    private void modifyEat(int foodValue, float saturationMultiplier, CallbackInfo ci) {
        this.foodLevel = Math.min(foodValue + this.foodLevel, Integer.MAX_VALUE);
        this.saturationLevel = Math.min(this.saturationLevel + (float) foodValue * saturationMultiplier * 2.0F, Integer.MAX_VALUE);
        ci.cancel();
    }
}
