package com.beyondomega.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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

import java.util.List;

public class MinotaurEntity extends Monster {

    // Synced slam data
    private static final EntityDataAccessor<Boolean> SLAMMING =
            SynchedEntityData.defineId(
                    MinotaurEntity.class,
                    EntityDataSerializers.BOOLEAN
            );

    private static final EntityDataAccessor<Integer> SLAM_ANIMATION_TICK =
            SynchedEntityData.defineId(
                    MinotaurEntity.class,
                    EntityDataSerializers.INT
            );

    // Animation state
    public final AnimationState slamAnimationState = new AnimationState();

    // Slam settings
    private static final int SLAM_DURATION = 64;
    private static final int SLAM_DAMAGE_TICK = 15;
    private static final int SLAM_COOLDOWN = 100;

    private int slamTicks = 0;
    private int slamCooldown = 0;
    private boolean slamDamageDone = false;

    public MinotaurEntity(
            EntityType<? extends Monster> entityType,
            Level level
    ) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(
            SynchedEntityData.Builder builder
    ) {
        super.defineSynchedData(builder);

        builder.define(SLAMMING, false);
        builder.define(SLAM_ANIMATION_TICK, 0);
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(
                1,
                new FloatGoal(this)
        );

        // Normal melee attack
        this.goalSelector.addGoal(
                2,
                new MeleeAttackGoal(
                        this,
                        1.2D,
                        false
                )
        );

        // Wander when no target
        this.goalSelector.addGoal(
                3,
                new WaterAvoidingRandomStrollGoal(
                        this,
                        1.0D
                )
        );

        this.goalSelector.addGoal(
                4,
                new LookAtPlayerGoal(
                        this,
                        Player.class,
                        8.0F
                )
        );

        this.targetSelector.addGoal(
                1,
                new NearestAttackableTargetGoal<>(
                        this,
                        Player.class,
                        true
                )
        );
    }

    @Override
    public void tick() {
        super.tick();

        // Cooldown
        if (this.slamCooldown > 0) {
            this.slamCooldown--;
        }

        // Currently slamming
        if (this.slamTicks > 0) {

            this.slamTicks--;

            // Update synced animation time
            this.entityData.set(
                    SLAM_ANIMATION_TICK,
                    SLAM_DURATION - this.slamTicks
            );

            // Stop moving
            this.getNavigation().stop();

            this.setDeltaMovement(
                    0.0D,
                    this.getDeltaMovement().y,
                    0.0D
            );

            // Deal damage at impact
            if (!this.slamDamageDone
                    && this.slamTicks
                    <= SLAM_DURATION - SLAM_DAMAGE_TICK) {

                this.doSlamDamage();
                this.slamDamageDone = true;
            }

            // Slam finished
            if (this.slamTicks <= 0) {

                this.entityData.set(
                        SLAMMING,
                        false
                );

                this.entityData.set(
                        SLAM_ANIMATION_TICK,
                        0
                );

                this.slamAnimationState.stop();
            }

            return;
        }

        // Look for target
        LivingEntity target = this.getTarget();

        if (target != null
                && target.isAlive()
                && this.slamCooldown <= 0
                && this.distanceTo(target) <= 4.0F) {

            // 10% chance per tick
            if (this.random.nextFloat() < 0.10F) {
                this.startSlam();
            }
        }
    }

    private void startSlam() {

        this.slamTicks = SLAM_DURATION;
        this.slamCooldown = SLAM_COOLDOWN;
        this.slamDamageDone = false;

        // START SYNCED SLAM
        this.entityData.set(
                SLAMMING,
                true
        );

        this.entityData.set(
                SLAM_ANIMATION_TICK,
                0
        );

        this.getNavigation().stop();

        if (!this.level().isClientSide()) {
            this.slamAnimationState.start(
                    this.tickCount
            );
        }
    }

    private void doSlamDamage() {

        // Damage MUST happen on server
        if (this.level().isClientSide()) {
            return;
        }

        List<LivingEntity> entities =
                this.level().getEntitiesOfClass(
                        LivingEntity.class,
                        this.getBoundingBox().inflate(4.0D),
                        entity -> entity != this
                );

        for (LivingEntity entity : entities) {

            entity.hurt(
                    this.damageSources().mobAttack(this),
                    12.0F
            );

            double x =
                    entity.getX() - this.getX();

            double z =
                    entity.getZ() - this.getZ();

            entity.knockback(
                    1.5D,
                    -x,
                    -z,
                    this.damageSources().mobAttack(this),
                    0.4F
            );
        }
    }

    public boolean isSlamming() {
        return this.entityData.get(SLAMMING);
    }

    public int getSlamAnimationTick() {
        return this.entityData.get(
                SLAM_ANIMATION_TICK
        );
    }

    public static AttributeSupplier.Builder createAttributes() {

        return Monster.createMonsterAttributes()

                .add(
                        Attributes.MAX_HEALTH,
                        40.0D
                )

                .add(
                        Attributes.MOVEMENT_SPEED,
                        0.25D
                )

                // Normal attack = weaker
                .add(
                        Attributes.ATTACK_DAMAGE,
                        3.0D
                );
    }
}

