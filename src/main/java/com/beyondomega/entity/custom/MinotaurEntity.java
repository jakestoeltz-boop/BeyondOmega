package com.beyondomega.entity.custom;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MinotaurEntity extends Monster {

    public MinotaurEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }
        // Define your animation states
        public final AnimationState idleState = new AnimationState();
        public final AnimationState runState = new AnimationState();
        public final AnimationState slamState = new AnimationState();

        // Add variables to handle your transitions
        public final AnimationState idleToRunState = new AnimationState();
        public final AnimationState runToIdleState = new AnimationState();

        // ... existing constructor and goals ...

    // AI Goals: This tells the mob how to behave
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this)); // Swims if in water
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false)); // Attacks targets
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D)); // Wanders around
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F)); // Looks at players

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true)); // Targets players
    }

    // Attributes: Sets the mob's health, speed, and damage
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D);
    }
}
