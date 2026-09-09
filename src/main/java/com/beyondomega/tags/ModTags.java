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

        public static final TagKey<Block> NEEDS_ZENITH_TOOL = createTag("needs_zenith_tool");
        public static final TagKey<Block> INCORRECT_FOR_ZENITH_TOOL = createTag("incorrect_for_zenith_tool");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        public static final TagKey<Item> ZENITH_REPAIRABLE = createTag("zenith_repairable");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, name));
        }
    }
}
