package com.fixdogs.fixdogstargetting.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.world.entity.ai.targeting.TargetingConditions.class)
public class TargetingConditionsMixin {

    /*
    * Redirect the call to the LivingEntity.isTeammate method in TargetingConditions.test(). This 'test'
    * method is used as a pre-condition for Wolves' goal 'OwnerHurtTargetGoal' to start.
    * */
    @Redirect(
            method = "test",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;isAlliedTo(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private boolean redirectIsTeammate(LivingEntity instance, Entity other) {
        return false;
    }
}

