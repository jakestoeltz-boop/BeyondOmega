package com.beyondomega.block.custom;

import com.beyondomega.BeyondOmega;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;

public class GreekPortalBlock extends Block {

    public static final ResourceKey<Level> GREEK_REALM =
            ResourceKey.create(
                    Registries.DIMENSION,
                    Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, "greek_realm")
            );

    public GreekPortalBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(QUADRANT, PortalQuadrant.NW)
        );
    }
    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(QUADRANT);
    }
    @Override
    protected void entityInside(
            BlockState state,
            Level level,
            BlockPos pos,
            Entity entity,
            InsideBlockEffectApplier effectApplier,
            boolean isPrecise
    ) {
        super.entityInside(state, level, pos, entity, effectApplier, isPrecise);

        if (level.isClientSide()) {
            return;
        }

        if (!(entity instanceof ServerPlayer player)) {
            return;
        }

        teleportToGreekRealm(player);
    }

    public static final EnumProperty<PortalQuadrant> QUADRANT =
            EnumProperty.create("quadrant", PortalQuadrant.class);


    private void teleportToGreekRealm(ServerPlayer player) {

        ServerLevel destination =
                player.level().getServer().getLevel(GREEK_REALM);

        if (destination == null) {
            return;
        }

        // Keep the player's current X/Z coordinates.
        int x = Mth.floor(player.getX());
        int z = Mth.floor(player.getZ());

        /*
         * Find the first safe Y position above the terrain.
         *
         * MOTION_BLOCKING_NO_LEAVES ignores tree leaves,
         * so we don't spawn on top of a tree canopy.
         */
        int surfaceY =
                destination.getHeight(
                        Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                        x,
                        z
                );

        Vec3 destinationPos = new Vec3(
                player.getX(),
                surfaceY,
                player.getZ()
        );

        TeleportTransition transition =
                new TeleportTransition(
                        destination,
                        destinationPos,
                        Vec3.ZERO,
                        player.getYRot(),
                        player.getXRot(),
                        TeleportTransition.PLAY_PORTAL_SOUND
                );

        player.teleport(transition);
    }
}