package com.beyondomega.tags;

import com.beyondomega.BeyondOmega;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        // =========================================================
        // ZENITH
        // =========================================================

        // For FUTURE blocks that require Zenith to mine.
        public static final TagKey<Block> NEEDS_ZENITH_TOOL =
                createTag("needs_zenith_tool");

        // Passed into the Zenith ToolMaterial.
        public static final TagKey<Block> INCORRECT_FOR_ZENITH_TOOL =
                createTag("incorrect_for_zenith_tool");


        // =========================================================
        // GREEK STEEL
        // =========================================================

        // Blocks that require Greek Steel or better.
        public static final TagKey<Block> NEEDS_GREEK_STEEL_TOOL =
                createTag("needs_greek_steel_tool");

        public static final TagKey<Block> INCORRECT_FOR_GREEK_STEEL_TOOL =
                createTag("incorrect_for_greek_steel_tool");


        // =========================================================
        // AETHER
        // =========================================================

        // Blocks that require Aether or better.
        public static final TagKey<Block> NEEDS_AETHER_TOOL =
                createTag("needs_aether_tool");

        public static final TagKey<Block> INCORRECT_FOR_AETHER_TOOL =
                createTag("incorrect_for_aether_tool");


        // =========================================================
        // ORICHALCUM
        // =========================================================

        // Blocks that require Orichalcum or better.
        public static final TagKey<Block> NEEDS_ORICHALCUM_TOOL =
                createTag("needs_orichalcum_tool");

        public static final TagKey<Block> INCORRECT_FOR_ORICHALCUM_TOOL =
                createTag("incorrect_for_orichalcum_tool");


        // =========================================================
        // STYGIAN
        // =========================================================

        // Blocks that require Stygian or better.
        public static final TagKey<Block> NEEDS_STYGIAN_TOOL =
                createTag("needs_stygian_tool");

        public static final TagKey<Block> INCORRECT_FOR_STYGIAN_TOOL =
                createTag("incorrect_for_stygian_tool");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(
                    Identifier.fromNamespaceAndPath(
                            BeyondOmega.MOD_ID,
                            name
                    )
            );
        }
    }


    public static class Items {

        public static final TagKey<Item> TRANSFORMABLE_ITEMS =
                createTag("transformable_items");

        public static final TagKey<Item> ZENITH_REPAIRABLE =
                createTag("zenith_repairable");

        public static final TagKey<Item> GREEK_STEEL_REPAIRABLE =
                createTag("greek_steel_repairable");

        public static final TagKey<Item> AETHER_REPAIRABLE =
                createTag("aether_repairable");

        public static final TagKey<Item> ORICHALCUM_REPAIRABLE =
                createTag("orichalcum_repairable");

        public static final TagKey<Item> STYGIAN_REPAIRABLE =
                createTag("stygian_repairable");


        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(
                    Identifier.fromNamespaceAndPath(
                            BeyondOmega.MOD_ID,
                            name
                    )
            );
        }
    }
}
