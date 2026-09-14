package com.beyondomega.entity.custom;

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

    public MinotaurEntity(EntityType<? extends MinotaurEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {

        // Basic movement
        this.goalSelector.addGoal(1, new FloatGoal(this));

        // Attack players
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));

        // Wander when no target exists
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8D));

        // Look at nearby players
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 12.0F));

        // Target players
        this.targetSelector.addGoal(
                1,
                new NearestAttackableTargetGoal<>(
                        this,
                        Player.class,
                        true
                )
        );
    }

    public static AttributeSupplier.Builder createAttributes() {

        return Monster.createMonsterAttributes()

                // Boss health
                .add(Attributes.MAX_HEALTH, 100.0D)

                // Movement speed
                .add(Attributes.MOVEMENT_SPEED, 0.28D)

                // Basic attack damage
                .add(Attributes.ATTACK_DAMAGE, 4.0D)

                // How far away it can notice players
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

}

