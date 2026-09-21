package com.beyondomega.event;

import com.beyondomega.BeyondOmega;
import com.mojang.datafixers.util.Pair;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;

import net.minecraft.network.chat.Component;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.Filterable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WrittenBookContent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.LecternBlockEntity;

import net.minecraft.world.level.levelgen.structure.Structure;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.List;


@EventBusSubscriber(modid = BeyondOmega.MOD_ID)
public final class RuinLecternEvents {


    // =========================================================
    // MINOTAUR LABYRINTH STRUCTURE ID
    // =========================================================

    private static final ResourceKey<Structure> MINOTAUR_LABYRINTH =
            ResourceKey.create(
                    Registries.STRUCTURE,
                    Identifier.fromNamespaceAndPath(
                            BeyondOmega.MOD_ID,
                            "minotaur_labyrinth"
                    )
            );


    private RuinLecternEvents() {
    }


    // =========================================================
    // LECTERN CLICK
    // =========================================================

    @SubscribeEvent
    public static void onLecternClicked(
            PlayerInteractEvent.RightClickBlock event
    ) {

        // Server only
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }


        BlockPos lecternPos =
                event.getPos();


        // Must actually be a lectern
        if (!level.getBlockState(lecternPos)
                .is(Blocks.LECTERN)) {

            return;
        }


        // Get the lectern block entity
        if (!(level.getBlockEntity(lecternPos)
                instanceof LecternBlockEntity lectern)) {

            return;
        }


        ItemStack currentBook =
                lectern.getBook();


        // Must contain a book
        if (currentBook.isEmpty()) {
            return;
        }


        WrittenBookContent currentContent =
                currentBook.get(
                        DataComponents.WRITTEN_BOOK_CONTENT
                );


        // Only written books
        if (currentContent == null) {
            return;
        }


        /*
         * If we've already converted this book,
         * don't run the locate operation again.
         */
        if (currentContent.title()
                .raw()
                .equals("Ariadne's Thread")) {

            return;
        }


        // =====================================================
        // GET THE LABYRINTH STRUCTURE DIRECTLY
        // =====================================================

        Holder.Reference<Structure> labyrinthHolder;

        try {

            labyrinthHolder =
                    level.registryAccess()
                            .lookupOrThrow(
                                    Registries.STRUCTURE
                            )
                            .getOrThrow(
                                    MINOTAUR_LABYRINTH
                            );

        } catch (Exception exception) {

            event.getEntity()
                    .sendSystemMessage(
                            Component.literal(
                                    "Could not find the Minotaur Labyrinth structure registry entry."
                            )
                    );

            return;
        }


        HolderSet<Structure> labyrinthSet =
                HolderSet.direct(
                        labyrinthHolder
                );


        // =====================================================
        // FIND NEAREST LABYRINTH
        // =====================================================

        Pair<BlockPos, Holder<Structure>> result =
                level.getChunkSource()
                        .getGenerator()
                        .findNearestMapStructure(
                                level,

                                labyrinthSet,

                                lecternPos,

                                /*
                                 * Radius is in chunks.
                                 *
                                 * 256 chunks gives us a very
                                 * generous search distance.
                                 */
                                256,

                                false
                        );


        if (result == null) {

            event.getEntity()
                    .sendSystemMessage(
                            Component.literal(
                                    "No Minotaur Labyrinth could be found nearby."
                            )
                    );

            return;
        }


        BlockPos labyrinthPos =
                result.getFirst();


        // =====================================================
        // CREATE THE NEW BOOK
        // =====================================================

        ItemStack locatorBook =
                new ItemStack(
                        Items.WRITTEN_BOOK
                );


        Component pageOne =
                Component.literal(
                        "Ariadne's Thread\n\n"
                                + "Traveler,\n\n"
                                + "I have discovered signs "
                                + "of the beast's prison."
                );


        Component pageTwo =
                Component.literal(
                        "Seek the Labyrinth.\n\n"
                                + "X: "
                                + labyrinthPos.getX()
                                + "\n"
                                + "Z: "
                                + labyrinthPos.getZ()
                                + "\n\n"
                                + "May the thread guide you."
                );


        WrittenBookContent newContent =
                new WrittenBookContent(

                        Filterable.passThrough(
                                "Ariadne's Thread"
                        ),

                        "Ariadne",

                        0,

                        List.of(

                                Filterable.passThrough(
                                        pageOne
                                ),

                                Filterable.passThrough(
                                        pageTwo
                                )
                        ),

                        true
                );


        locatorBook.set(
                DataComponents.WRITTEN_BOOK_CONTENT,
                newContent
        );


        // =====================================================
        // REPLACE THE BOOK
        // =====================================================

        lectern.setBook(
                locatorBook,
                event.getEntity()
        );


        lectern.setChanged();


        /*
         * Important:
         * force the changed block entity to sync
         * to the client.
         */
        level.sendBlockUpdated(
                lecternPos,
                level.getBlockState(lecternPos),
                level.getBlockState(lecternPos),
                3
        );


        // Confirmation while testing
        event.getEntity()
                .sendSystemMessage(
                        Component.literal(
                                "Ariadne's notes reveal the Labyrinth at X: "
                                        + labyrinthPos.getX()
                                        + ", Z: "
                                        + labyrinthPos.getZ()
                        )
                );
    }
}