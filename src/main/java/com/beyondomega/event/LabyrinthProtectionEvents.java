package com.beyondomega.event;

import com.beyondomega.BeyondOmega;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;
import net.neoforged.neoforge.event.level.PistonEvent;
import net.neoforged.neoforge.event.level.block.BreakBlockEvent;


@EventBusSubscriber(modid = BeyondOmega.MOD_ID)
public final class LabyrinthProtectionEvents {


    // =========================================================
    // PROTECTED STRUCTURE TAG
    // =========================================================

    private static final TagKey<Structure> PROTECTED_STRUCTURES =
            TagKey.create(
                    Registries.STRUCTURE,
                    Identifier.fromNamespaceAndPath(
                            BeyondOmega.MOD_ID,
                            "protected_structures"
                    )
            );


    private LabyrinthProtectionEvents() {
    }


    // =========================================================
    // PREVENT PLAYERS FROM BREAKING BLOCKS
    // =========================================================

    @SubscribeEvent
    public static void onBlockBreak(
            BreakBlockEvent event
    ) {

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }


        Player player =
                event.getPlayer();


        // Creative mode can still edit the labyrinth.
        if (player.isCreative()) {
            return;
        }


        if (isInsideProtectedStructure(
                level,
                event.getPos()
        )) {

            event.setCanceled(true);

            /*
             * Tell the client that the block still exists,
             * since we're cancelling server-side.
             */
            event.setNotifyClient(true);
        }
    }


    // =========================================================
    // PREVENT BLOCK PLACEMENT
    // =========================================================

    @SubscribeEvent
    public static void onBlockPlace(
            BlockEvent.EntityPlaceEvent event
    ) {

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }


        /*
         * Creative players are allowed to build.
         */
        if (event.getEntity() instanceof Player player
                && player.isCreative()) {

            return;
        }


        /*
         * Cancel any entity-caused block placement
         * inside the labyrinth.
         *
         * This includes:
         *
         * players
         * Endermen
         * other entity-based placement
         */
        if (isInsideProtectedStructure(
                level,
                event.getPos()
        )) {

            event.setCanceled(true);
        }
    }


    // =========================================================
    // TNT / CREEPERS / EXPLOSIONS
    // =========================================================

    @SubscribeEvent
    public static void onExplosion(
            ExplosionEvent.Detonate event
    ) {

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }


        /*
         * Do NOT cancel the actual explosion.
         *
         * We only remove protected labyrinth blocks
         * from the list of blocks it can destroy.
         *
         * This means:
         *
         * explosion sound still happens
         * particles still happen
         * entities can still take damage
         * knockback still works
         *
         * but the labyrinth stays intact.
         */
        event.getAffectedBlocks().removeIf(
                pos -> isInsideProtectedStructure(
                        level,
                        pos
                )
        );
    }


    // =========================================================
    // WATER / LAVA / FIRE ITEMS USED ON BLOCKS
    // =========================================================

    @SubscribeEvent
    public static void onRightClickBlock(
            PlayerInteractEvent.RightClickBlock event
    ) {

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }


        Player player =
                event.getEntity();


        // Creative mode can still edit/test.
        if (player.isCreative()) {
            return;
        }


        ItemStack item =
                event.getItemStack();


        boolean blockedItem =
                item.is(Items.WATER_BUCKET)
                        || item.is(Items.LAVA_BUCKET)
                        || item.is(Items.BUCKET)
                        || item.is(Items.FLINT_AND_STEEL)
                        || item.is(Items.FIRE_CHARGE);


        if (!blockedItem) {
            return;
        }


        BlockPos clickedPos =
                event.getPos();


        Direction face =
                event.getFace();


        BlockPos placementPos =
                face != null
                        ? clickedPos.relative(face)
                        : clickedPos;


        /*
         * Check both:
         *
         * the block being clicked
         * the block next to it where fluid/fire may appear
         */
        if (isInsideProtectedStructure(
                level,
                clickedPos
        ) || isInsideProtectedStructure(
                level,
                placementPos
        )) {

            event.setCanceled(true);

            event.setCancellationResult(
                    InteractionResult.FAIL
            );
        }
    }


    // =========================================================
    // WATER / LAVA / EMPTY BUCKET USE
    // =========================================================

    @SubscribeEvent
    public static void onRightClickItem(
            PlayerInteractEvent.RightClickItem event
    ) {

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }


        Player player =
                event.getEntity();


        if (player.isCreative()) {
            return;
        }


        ItemStack item =
                event.getItemStack();


        boolean isBucket =
                item.is(Items.BUCKET)
                        || item.is(Items.WATER_BUCKET)
                        || item.is(Items.LAVA_BUCKET);


        if (!isBucket) {
            return;
        }


        /*
         * RightClickItem is used when the interaction
         * isn't already handled as a RightClickBlock.
         *
         * If the player is inside or near the labyrinth,
         * prevent bucket usage.
         */
        if (isNearProtectedStructure(
                level,
                player.blockPosition(),
                6
        )) {

            event.setCanceled(true);

            event.setCancellationResult(
                    InteractionResult.FAIL
            );
        }
    }


    // =========================================================
    // FLUID-CAUSED BLOCK CHANGES
    // =========================================================

    @SubscribeEvent
    public static void onFluidChangeBlock(
            BlockEvent.FluidPlaceBlockEvent event
    ) {

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }


        /*
         * Stops fluid-generated block changes such as:
         *
         * lava + water -> cobblestone
         * lava + water -> stone
         * lava + water -> obsidian
         *
         * and lava-related block placement changes.
         */
        if (isInsideProtectedStructure(
                level,
                event.getPos()
        )) {

            event.setCanceled(true);
        }
    }


    // =========================================================
    // PISTONS
    // =========================================================

    @SubscribeEvent
    public static void onPiston(
            PistonEvent.Pre event
    ) {

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }


        /*
         * Pistons can push up to 12 blocks and
         * slime/honey setups can affect nearby blocks.
         *
         * Instead of trying to inspect every block in
         * the piston resolver, we simply disable piston
         * movement if the piston is close enough to a
         * protected structure to possibly affect it.
         *
         * 13 blocks safely covers normal piston reach.
         */
        if (isNearProtectedStructure(
                level,
                event.getPos(),
                13
        )) {

            event.setCanceled(true);
        }
    }


    // =========================================================
    // ENDERMEN
    // =========================================================

    @SubscribeEvent
    public static void onMobGriefing(
            EntityMobGriefingEvent event
    ) {

        /*
         * Only care about Endermen.
         */
        if (!(event.getEntity() instanceof EnderMan enderman)) {
            return;
        }


        if (!(enderman.level() instanceof ServerLevel level)) {
            return;
        }


        /*
         * Disable Enderman block pickup / placement
         * when they are inside or very close to the
         * labyrinth.
         */
        if (isNearProtectedStructure(
                level,
                enderman.blockPosition(),
                3
        )) {

            event.setCanGrief(false);
        }
    }


    // =========================================================
    // CHECK IF POSITION IS INSIDE A PROTECTED STRUCTURE
    // =========================================================

    private static boolean isInsideProtectedStructure(
            ServerLevel level,
            BlockPos pos
    ) {

        StructureStart structureStart =
                level.structureManager()
                        .getStructureWithPieceAt(
                                pos,
                                PROTECTED_STRUCTURES
                        );


        return structureStart.isValid();
    }


    // =========================================================
    // CHECK AREA AROUND POSITION
    // =========================================================

    private static boolean isNearProtectedStructure(
            ServerLevel level,
            BlockPos center,
            int radius
    ) {

        /*
         * First check the center.
         *
         * This makes the common case very fast.
         */
        if (isInsideProtectedStructure(
                level,
                center
        )) {

            return true;
        }


        /*
         * Check the outside shell of the requested radius.
         *
         * We don't need to test every single interior block
         * because we're mainly trying to determine whether a
         * structure is close enough to the entity/player/piston.
         */
        for (int x = -radius; x <= radius; x++) {

            for (int y = -radius; y <= radius; y++) {

                for (int z = -radius; z <= radius; z++) {

                    /*
                     * Only check positions on the outside
                     * shell of this cube.
                     *
                     * This saves a lot of unnecessary checks.
                     */
                    if (Math.abs(x) != radius
                            && Math.abs(y) != radius
                            && Math.abs(z) != radius) {

                        continue;
                    }


                    BlockPos checkPos =
                            center.offset(
                                    x,
                                    y,
                                    z
                            );


                    if (isInsideProtectedStructure(
                            level,
                            checkPos
                    )) {

                        return true;
                    }
                }
            }
        }


        return false;
    }
}