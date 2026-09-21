package com.beyondomega.block.custom;

import com.beyondomega.block.ModBlocks;
import com.beyondomega.entity.ModEntities;
import com.beyondomega.entity.custom.MinotaurEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class MinotaurArenaTriggerBlock extends Block {

    /*
     * How far from the entrance trigger we will search
     * for the hidden BARRIER that marks the boss spawn.
     */
    private static final int MARKER_SEARCH_RADIUS = 48;
    private static final int MARKER_SEARCH_HEIGHT = 24;


    public MinotaurArenaTriggerBlock(Properties properties) {
        super(properties);
    }


    // =========================================================
    // INVISIBLE
    // =========================================================

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }


    // =========================================================
    // PLAYER ENTERS TRIGGER
    // =========================================================

    @Override
    protected void entityInside(
            BlockState state,
            Level level,
            BlockPos pos,
            Entity entity,
            InsideBlockEffectApplier effectApplier,
            boolean isPrecise
    ) {

        super.entityInside(
                state,
                level,
                pos,
                entity,
                effectApplier,
                isPrecise
        );


        // Only run server-side.
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }


        // Only players can trigger the boss.
        if (!(entity instanceof ServerPlayer player)) {
            return;
        }


        // Find the hidden barrier marker in the boss arena.
        BlockPos markerPos =
                findBossSpawnMarker(
                        serverLevel,
                        pos
                );


        // No marker means the boss has already been spawned.
        if (markerPos == null) {
            return;
        }


        /*
         * The barrier marker should be ONE block underneath
         * the arena floor.
         *
         * Example:
         *
         * Minotaur feet
         *      X
         *
         * arena floor
         * █████████
         *
         * barrier marker
         *     B
         *
         * Therefore we spawn 2 blocks above the marker.
         */
        BlockPos spawnPos =
                markerPos.above(2);


        // Spawn the boss.
        MinotaurEntity minotaur =
                ModEntities.MINOTAUR.get().spawn(
                        serverLevel,
                        spawnPos,
                        EntitySpawnReason.TRIGGERED
                );


        // If spawning somehow failed, leave everything
        // intact so it can try again.
        if (minotaur == null) {
            return;
        }


        // =====================================================
        // MAKE MINOTAUR FACE THE PLAYER
        // =====================================================

        double dx =
                player.getX()
                        - minotaur.getX();

        double dz =
                player.getZ()
                        - minotaur.getZ();


        float yaw =
                (float) (
                        Math.toDegrees(
                                Math.atan2(
                                        dz,
                                        dx
                                )
                        )
                                - 90.0D
                );


        minotaur.setYRot(yaw);
        minotaur.setYHeadRot(yaw);
        minotaur.setYBodyRot(yaw);


        // =====================================================
        // REMOVE THE SPAWN MARKER
        // =====================================================

        /*
         * This is what permanently remembers that
         * this particular labyrinth has already spawned
         * its Minotaur.
         *
         * Once this barrier is gone, the trigger can
         * never spawn another Minotaur here.
         */
        serverLevel.setBlock(
                markerPos,
                Blocks.AIR.defaultBlockState(),
                3
        );


        // Remove the trigger block the player touched.
        serverLevel.setBlock(
                pos,
                Blocks.AIR.defaultBlockState(),
                3
        );
    }


    // =========================================================
    // FIND BOSS SPAWN MARKER
    // =========================================================

    private BlockPos findBossSpawnMarker(
            ServerLevel level,
            BlockPos triggerPos
    ) {

        BlockPos min =
                triggerPos.offset(
                        -MARKER_SEARCH_RADIUS,
                        -MARKER_SEARCH_HEIGHT,
                        -MARKER_SEARCH_RADIUS
                );


        BlockPos max =
                triggerPos.offset(
                        MARKER_SEARCH_RADIUS,
                        MARKER_SEARCH_HEIGHT,
                        MARKER_SEARCH_RADIUS
                );


        for (BlockPos checkPos :
                BlockPos.betweenClosed(
                        min,
                        max
                )) {


            /*
             * We use ONE vanilla barrier block underneath
             * the boss arena as the spawn marker.
             */
            if (level.getBlockState(checkPos)
                    .is(Blocks.BARRIER)) {

                return checkPos.immutable();
            }
        }


        return null;
    }
}