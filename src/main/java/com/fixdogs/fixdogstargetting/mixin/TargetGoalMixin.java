package com.fixdogs.fixdogstargetting.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.scores.PlayerTeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TargetGoal.class)
public abstract class TargetGoalMixin {
    /*
    * Mobs check teams as part of their TargetGoal. This mixin disables the check by redirecting
    * the getTeam method, effectively acting like the mob doesn't have a team.
    * */
    @Redirect(
            method = "canContinueToUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;getTeam()Lnet/minecraft/world/scores/PlayerTeam;"
            )
    )
    PlayerTeam redirectGetTeam(Mob instance){
        return null;
    }
}
