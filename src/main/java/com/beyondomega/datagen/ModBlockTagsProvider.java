package com.beyondomega.datagen;

import com.beyondomega.BeyondOmega;
import com.beyondomega.block.ModBlocks;
import com.beyondomega.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BeyondOmega.MOD_ID);
    }
/*
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ZENITH_END_ORE.getKey())
                .add(ModBlocks.ZENITH_BLOCK.getKey());

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.ZENITH_END_ORE.getKey());

        tag(ModTags.Blocks.NEEDS_ZENITH_TOOL)
                .add(ModBlocks.ZENITH_BLOCK.getKey());

        tag(ModTags.Blocks.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_ZENITH_TOOL);
        */
@Override
protected void addTags(HolderLookup.Provider provider) {
    // 1. Make Zenith Core Ore mineable with a pickaxe
    this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.ZENITH_END_ORE.getKey())
            .add(ModBlocks.ZENITH_BLOCK.getKey())
            .add(ModBlocks.GREEK_PORTAL_FRAME.getKey())
            .add(ModBlocks.KEYSTONE_ORE.getKey());


    // 2. Add Zenith Core Ore to your custom Zenith requirement tag
    this.tag(ModTags.Blocks.NEEDS_ZENITH_TOOL)
            .add(ModBlocks.ZENITH_BLOCK.getKey());

    this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.KEYSTONE_ORE.getKey())
            .add(ModBlocks.GREEK_PORTAL_FRAME.getKey());

    this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
            .add(ModBlocks.ZENITH_END_ORE.getKey());

    // 3. Mark Zenith-level blocks as INCORRECT for Netherite tools
    this.tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
            .addTag(ModTags.Blocks.NEEDS_ZENITH_TOOL);

    this.tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
            .addTag(ModTags.Blocks.NEEDS_ZENITH_TOOL);

    this.tag(BlockTags.DIRT)
            .add(ModBlocks.ANCIENT_DIRT.getKey())
            .add(ModBlocks.ANCIENT_GRASS_BLOCK.getKey());

    this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(ModBlocks.ANCIENT_DIRT.getKey())
            .add(ModBlocks.ANCIENT_GRASS_BLOCK.getKey());

        }

    }
