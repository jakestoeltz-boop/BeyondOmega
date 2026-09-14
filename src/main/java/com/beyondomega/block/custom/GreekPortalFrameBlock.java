package com.beyondomega.block.custom;

import com.beyondomega.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GreekPortalFrameBlock extends Block {

    public GreekPortalFrameBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void setPlacedBy(
            Level level,
            BlockPos pos,
            BlockState state,
            LivingEntity placer,
            ItemStack stack
    ) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (!level.isClientSide()) {
            tryCreatePortal(level, pos);
        }
    }

    private void tryCreatePortal(Level level, BlockPos placedPos) {

        /*
         * The block just placed could be anywhere in the 4x4 ring.
         *
         * So we check every possible 4x4 starting corner
         * within 3 blocks of the placed block.
         */
        for (int offsetX = -3; offsetX <= 0; offsetX++) {
            for (int offsetZ = -3; offsetZ <= 0; offsetZ++) {

                BlockPos corner = placedPos.offset(offsetX, 0, offsetZ);

                if (isValidPortalFrame(level, corner)) {
                    createPortal(level, corner);
                    return;
                }
            }
        }
    }

    private boolean isValidPortalFrame(Level level, BlockPos corner) {

        for (int x = 0; x < 4; x++) {
            for (int z = 0; z < 4; z++) {

                BlockPos checkPos = corner.offset(x, 0, z);

                boolean border =
                        x == 0 ||
                                x == 3 ||
                                z == 0 ||
                                z == 3;

                if (border) {

                    if (!level.getBlockState(checkPos)
                            .is(ModBlocks.GREEK_PORTAL_FRAME.get())) {
                        return false;
                    }

                } else {

                    BlockState state = level.getBlockState(checkPos);

                    if (!state.isAir() &&
                            !state.is(ModBlocks.GREEK_PORTAL.get())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void createPortal(Level level, BlockPos corner) {

        level.setBlock(
                corner.offset(1, 0, 1),
                ModBlocks.GREEK_PORTAL.get()
                        .defaultBlockState()
                        .setValue(GreekPortalBlock.QUADRANT, PortalQuadrant.NW),
                Block.UPDATE_ALL
        );

        level.setBlock(
                corner.offset(2, 0, 1),
                ModBlocks.GREEK_PORTAL.get()
                        .defaultBlockState()
                        .setValue(GreekPortalBlock.QUADRANT, PortalQuadrant.NE),
                Block.UPDATE_ALL
        );

        level.setBlock(
                corner.offset(1, 0, 2),
                ModBlocks.GREEK_PORTAL.get()
                        .defaultBlockState()
                        .setValue(GreekPortalBlock.QUADRANT, PortalQuadrant.SW),
                Block.UPDATE_ALL
        );

        level.setBlock(
                corner.offset(2, 0, 2),
                ModBlocks.GREEK_PORTAL.get()
                        .defaultBlockState()
                        .setValue(GreekPortalBlock.QUADRANT, PortalQuadrant.SE),
                Block.UPDATE_ALL
        );
    }
}
