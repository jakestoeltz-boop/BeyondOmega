package com.beyondomega.entity.custom;

import com.beyondomega.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class MinotaurEntity extends Monster {

    // =========================================================
    // SYNCED SLAM DATA
    // =========================================================

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

    // =========================================================
// BEACON COMPLETION SETTINGS
// =========================================================

    // Horizontal distance to search around the Minotaur.
    private static final int BEACON_SEARCH_RADIUS = 50;

    // How far upward to search for the beacon.
    private static final int BEACON_SEARCH_HEIGHT = 96;

    private boolean beaconChanged = false;


    // =========================================================
    // ANIMATION STATE
    // =========================================================

    public final AnimationState slamAnimationState =
            new AnimationState();

    private final ServerBossEvent bossEvent;


    // =========================================================
    // NORMAL ATTACK SETTINGS
    // =========================================================

    // How far away his normal melee attack can hit.
    private static final double MELEE_ATTACK_RANGE = 5.5D;


    // =========================================================
    // SLAM SETTINGS
    // =========================================================

    // Total animation length.
    private static final int SLAM_DURATION = 64;

    // Tick during the animation where the shockwave actually hits.
    private static final int SLAM_DAMAGE_TICK = 15;

    // 100 ticks = 5 seconds.
    private static final int SLAM_COOLDOWN = 250;

    private static final int SLAM_WINDUP_DURATION = 10;

    // How close the player has to be before the Minotaur
    // is allowed to start the slam.
    private static final float SLAM_TRIGGER_RANGE = 9.0F;

    // Actual horizontal damage radius.
    private static final double SLAM_DAMAGE_RADIUS = 10.0D;

    // How far above/below the Minotaur the slam can hit.
    private static final double SLAM_VERTICAL_RANGE = 4.0D;

    // Damage dealt by the slam.
    private static final float SLAM_DAMAGE = 40.0F;

    // Strength of the knockback.
    private static final double SLAM_KNOCKBACK = 5D;


    // =========================================================
    // SLAM STATE
    // =========================================================

    private int slamTicks = 0;
    private int slamCooldown = 0;
    private boolean slamDamageDone = false;
    private int slamWindupTicks = 0;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MinotaurEntity(
            EntityType<? extends Monster> entityType,
            Level level
    ) {
        super(entityType, level);
        this.setPersistenceRequired();
        this.setCustomName(null);
        this.setCustomNameVisible(false);
        this.xpReward = 650;
        this.bossEvent = new ServerBossEvent(
                this.getUUID(),
                Component.literal("Minotaur"),
                BossEvent.BossBarColor.RED,
                BossEvent.BossBarOverlay.PROGRESS
        );

        this.bossEvent.setDarkenScreen(true);
    }
    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);

        this.setCustomName(null);
        this.setCustomNameVisible(false);
    }
    //sounds start
    @Override
    protected SoundEvent getAmbientSound() {

        return ModSounds.MINOTAUR_AMBIENT.value();
    }
    @Override
    protected SoundEvent getDeathSound() {

        return ModSounds.MINOTAUR_DEATH.value();
    }
    @Override
    protected void playStepSound(
            BlockPos pos,
            BlockState blockState
    ) {

        this.playSound(
                ModSounds.MINOTAUR_STEP.value(),
                0.8F,
                0.9F
        );
    }
    @Override
    protected void playAttackSound() {

        this.playSound(
                ModSounds.MINOTAUR_ATTACK.value(),
                1.5F,
                0.95F + this.random.nextFloat() * 0.1F
        );
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {

        if (source.getEntity() instanceof Player) {
            return ModSounds.MINOTAUR_HURT.value();
        }

        return null;
    }
    //sounds end
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    // =========================================================
    // SYNCED ENTITY DATA
    // =========================================================

    @Override
    protected void defineSynchedData(
            SynchedEntityData.Builder builder
    ) {
        super.defineSynchedData(builder);

        builder.define(SLAMMING, false);
        builder.define(SLAM_ANIMATION_TICK, 0);
    }
    @Override
    public void die(DamageSource source) {

        super.die(source);

        if (this.beaconChanged) {
            return;
        }

        if (!(this.level() instanceof ServerLevel level)) {
            return;
        }

        this.beaconChanged = true;

        this.changeBeaconToDefeated(level);
    }
    private void changeBeaconToDefeated(
            ServerLevel level
    ) {

        BlockPos center =
                this.blockPosition();

        BlockPos minPos =
                center.offset(
                        -BEACON_SEARCH_RADIUS,
                        0,
                        -BEACON_SEARCH_RADIUS
                );

        BlockPos maxPos =
                center.offset(
                        BEACON_SEARCH_RADIUS,
                        BEACON_SEARCH_HEIGHT,
                        BEACON_SEARCH_RADIUS
                );

        for (BlockPos pos :
                BlockPos.betweenClosed(
                        minPos,
                        maxPos
                )) {

            // Change red stained glass to light blue.
            if (level.getBlockState(pos)
                    .is(Blocks.STAINED_GLASS.red())) {

                level.setBlock(
                        pos,
                        Blocks.STAINED_GLASS.lightBlue()
                                .defaultBlockState(),
                        3
                );
            }

            // Also works with glass panes if you use any.
            if (level.getBlockState(pos)
                    .is(Blocks.STAINED_GLASS.red())) {

                level.setBlock(
                        pos,
                        Blocks.STAINED_GLASS.lightBlue()
                                .defaultBlockState(),
                        3
                );
            }
        }
    }


    // =========================================================
    // AI GOALS
    // =========================================================

    @Override
    protected void registerGoals() {

        // Float in water.
        this.goalSelector.addGoal(
                1,
                new FloatGoal(this)
        );


        // Custom melee attack with increased reach.
        this.goalSelector.addGoal(
                2,
                new MinotaurMeleeAttackGoal(
                        this,
                        1.2D,
                        false
                )
        );


        // Wander around when no target exists.
        this.goalSelector.addGoal(
                3,
                new WaterAvoidingRandomStrollGoal(
                        this,
                        1.0D
                )
        );


        // Look at nearby players.
        this.goalSelector.addGoal(
                4,
                new LookAtPlayerGoal(
                        this,
                        Player.class,
                        8.0F
                )
        );


        // Target players.
        this.targetSelector.addGoal(
                1,
                new NearestAttackableTargetGoal<>(
                        this,
                        Player.class,
                        true
                )
        );
    }


    // =========================================================
    // TICK
    // =========================================================

    @Override
    public void tick() {

        super.tick();
        if (!this.level().isClientSide()) {
            this.bossEvent.setProgress(
                    this.getHealth() / this.getMaxHealth()
            );
        }


        // -----------------------------------------------------
        // SLAM COOLDOWN
        // -----------------------------------------------------

        if (this.slamCooldown > 0) {
            this.slamCooldown--;
        }

        // =========================================================
// PRE-SLAM WARNING
// =========================================================

        if (this.slamWindupTicks > 0) {

            this.slamWindupTicks--;

            // Keep Minotaur completely still during warning.
            this.getNavigation().stop();

            this.setDeltaMovement(
                    0.0D,
                    this.getDeltaMovement().y,
                    0.0D
            );


            // Warning finished -> begin actual slam animation.
            if (this.slamWindupTicks <= 0) {

                this.beginActualSlam();
            }

            return;
        }

        // -----------------------------------------------------
        // CURRENTLY SLAMMING
        // -----------------------------------------------------

        if (this.slamTicks > 0) {

            this.slamTicks--;


            // How many ticks have elapsed since the slam started.
            int slamElapsedTicks =
                    SLAM_DURATION - this.slamTicks;


            // Sync animation time to client.
            this.entityData.set(
                    SLAM_ANIMATION_TICK,
                    slamElapsedTicks
            );


            // Completely stop navigation during the slam.
            this.getNavigation().stop();


            // Stop horizontal movement,
            // but preserve vertical movement/gravity.
            this.setDeltaMovement(
                    0.0D,
                    this.getDeltaMovement().y,
                    0.0D
            );


            // -------------------------------------------------
            // SLAM IMPACT
            // -------------------------------------------------

            if (!this.slamDamageDone
                    && slamElapsedTicks >= SLAM_DAMAGE_TICK) {

                this.doSlamDamage();

                this.slamDamageDone = true;
            }


            // -------------------------------------------------
            // SLAM FINISHED
            // -------------------------------------------------

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


        // =====================================================
        // CHECK FOR NEW SLAM
        // =====================================================

        LivingEntity target =
                this.getTarget();


        if (target != null
                && target.isAlive()
                && this.slamCooldown <= 0
                && this.distanceTo(target) <= SLAM_TRIGGER_RANGE) {

            // 10% chance every tick while eligible.
            if (this.random.nextFloat() < 0.10F) {

                this.startSlam();
            }
        }
    }


    // =========================================================
    // START SLAM
    // =========================================================

    private void startSlam() {

        // Begin warning phase first
        this.slamWindupTicks = SLAM_WINDUP_DURATION;

        // Start cooldown now so another slam cannot be queued
        this.slamCooldown = SLAM_COOLDOWN;

        this.slamDamageDone = false;

        // Stop chasing the player
        this.getNavigation().stop();

        this.setDeltaMovement(
                0.0D,
                this.getDeltaMovement().y,
                0.0D
        );


        // Play the warning roar BEFORE the slam animation
        if (!this.level().isClientSide()) {

            this.playSound(
                    ModSounds.MINOTAUR_BEFORE_SLAM.value(),
                    2.0F,
                    1.0F
            );
        }
    }
    private void beginActualSlam() {

        this.slamTicks = SLAM_DURATION;

        this.slamDamageDone = false;


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


    // =========================================================
    // SLAM DAMAGE
    // =========================================================

    private void doSlamDamage() {

        // Damage must only happen on the server.
        if (this.level().isClientSide()) {
            return;
        }
        this.playSound(
                ModSounds.MINOTAUR_SLAM.value(),
                2.5F,
                0.9F
        );

        /*
         * First grab everything inside a large rectangular
         * search box.
         *
         * We then perform a circular distance check below,
         * so the actual slam isn't a giant square.
         */
        List<LivingEntity> entities =
                this.level().getEntitiesOfClass(
                        LivingEntity.class,

                        this.getBoundingBox().inflate(
                                SLAM_DAMAGE_RADIUS,
                                SLAM_VERTICAL_RANGE,
                                SLAM_DAMAGE_RADIUS
                        ),

                        entity ->
                                entity != this
                                        && entity.isAlive()
                );


        for (LivingEntity entity : entities) {

            double x =
                    entity.getX() - this.getX();

            double z =
                    entity.getZ() - this.getZ();


            // -------------------------------------------------
            // HORIZONTAL CIRCULAR RADIUS
            // -------------------------------------------------

            double horizontalDistanceSqr =
                    (x * x) + (z * z);


            if (horizontalDistanceSqr
                    > SLAM_DAMAGE_RADIUS
                    * SLAM_DAMAGE_RADIUS) {

                continue;
            }


            // -------------------------------------------------
            // VERTICAL RANGE
            // -------------------------------------------------

            if (Math.abs(
                    entity.getY() - this.getY()
            ) > SLAM_VERTICAL_RANGE) {

                continue;
            }


            // -------------------------------------------------
            // DON'T HIT THROUGH LABYRINTH WALLS
            // -------------------------------------------------

            if (!this.getSensing()
                    .hasLineOfSight(entity)) {

                continue;
            }


            // -------------------------------------------------
            // DAMAGE
            // -------------------------------------------------

            entity.hurt(
                    this.damageSources()
                            .mobAttack(this),

                    SLAM_DAMAGE
            );


            // -------------------------------------------------
            // KNOCKBACK
            // -------------------------------------------------

            entity.knockback(
                    SLAM_KNOCKBACK,
                    -x,
                    -z,
                    this.damageSources()
                            .mobAttack(this),
                    0.4F
            );
        }
    }


    // =========================================================
    // SLAM GETTERS
    // =========================================================


    @Override
    public void startSeenByPlayer(ServerPlayer player) {

        super.startSeenByPlayer(player);

        this.bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {

        super.stopSeenByPlayer(player);

        this.bossEvent.removePlayer(player);
    }

    public boolean isSlamming() {

        return this.entityData.get(
                SLAMMING
        );
    }


    public int getSlamAnimationTick() {

        return this.entityData.get(
                SLAM_ANIMATION_TICK
        );
    }
    public boolean isPreparingSlam() {

        return this.slamWindupTicks > 0;
    }


    // =========================================================
    // CUSTOM MELEE ATTACK GOAL
    // =========================================================

    private static class MinotaurMeleeAttackGoal
            extends MeleeAttackGoal {

        private final MinotaurEntity minotaur;


        public MinotaurMeleeAttackGoal(
                MinotaurEntity minotaur,
                double speedModifier,
                boolean followingTargetEvenIfNotSeen
        ) {

            super(
                    minotaur,
                    speedModifier,
                    followingTargetEvenIfNotSeen
            );

            this.minotaur =
                    minotaur;
        }


        @Override
        protected boolean canPerformAttack(
                LivingEntity target
        ) {

            // No regular punches while the Minotaur is slamming.
            if (this.minotaur.isSlamming()
                    || this.minotaur.isPreparingSlam()) {

                return false;
            }


            // Don't let him attack through labyrinth walls.
            if (!this.minotaur
                    .getSensing()
                    .hasLineOfSight(target)) {

                return false;
            }


            // Custom extended melee reach.
            return this.minotaur
                    .distanceToSqr(target)

                    <= MELEE_ATTACK_RANGE
                    * MELEE_ATTACK_RANGE;
        }
    }


    // =========================================================
    // ATTRIBUTES
    // =========================================================

    public static AttributeSupplier.Builder createAttributes() {

        return Monster.createMonsterAttributes()

                .add(
                        Attributes.MAX_HEALTH,
                        750.0D
                )

                .add(
                        Attributes.MOVEMENT_SPEED,
                        0.25D
                )

                .add(
                        Attributes.ATTACK_DAMAGE,
                        15.0D
                );
    }
}

