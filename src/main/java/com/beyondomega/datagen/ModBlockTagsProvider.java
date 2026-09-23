package com.beyondomega.datagen;

import com.beyondomega.BeyondOmega;
import com.beyondomega.block.ModBlocks;
import com.beyondomega.tags.ModTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        super(output, lookupProvider, BeyondOmega.MOD_ID);
    }


    /*
     * ============================================================
     * CUSTOM GREEK TOOL PROGRESSION
     * ============================================================
     *
     * Diamond
     *    ↓
     * Greek Steel
     *    ↓
     * Aether
     *    ↓
     * Orichalcum
     *    ↓
     * Stygian
     *
     *
     * Separate progression:
     *
     * Netherite
     *    ↓
     * Zenith
     *
     *
     * When adding a new Greek progression tier:
     *
     * 1. Create NEEDS_NEW_TOOL in ModTags
     * 2. Create INCORRECT_FOR_NEW_TOOL in ModTags
     * 3. Add both tags to these lists in the SAME position
     * 4. Create the ToolMaterial using INCORRECT_FOR_NEW_TOOL
     */


    /*
     * These are the requirements for each level of the
     * Greek progression.
     */
    private static final List<TagKey<Block>> CUSTOM_NEEDS_TAGS = List.of(
            ModTags.Blocks.NEEDS_GREEK_STEEL_TOOL,
            ModTags.Blocks.NEEDS_AETHER_TOOL,
            ModTags.Blocks.NEEDS_ORICHALCUM_TOOL,
            ModTags.Blocks.NEEDS_STYGIAN_TOOL
    );


    /*
     * These are the incorrect tags used by each ToolMaterial.
     *
     * The position MUST match CUSTOM_NEEDS_TAGS.
     */
    private static final List<TagKey<Block>> CUSTOM_INCORRECT_TAGS = List.of(
            ModTags.Blocks.INCORRECT_FOR_GREEK_STEEL_TOOL,
            ModTags.Blocks.INCORRECT_FOR_AETHER_TOOL,
            ModTags.Blocks.INCORRECT_FOR_ORICHALCUM_TOOL,
            ModTags.Blocks.INCORRECT_FOR_STYGIAN_TOOL
    );


    @Override
    protected void addTags(HolderLookup.Provider provider) {

        /*
         * ============================================================
         * PICKAXE-MINEABLE BLOCKS
         * ============================================================
         */

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ZENITH_END_ORE.getKey())
                .add(ModBlocks.ZENITH_BLOCK.getKey())

                .add(ModBlocks.GREEK_PORTAL_FRAME.getKey())

                .add(ModBlocks.LIMESTONE.getKey())
                .add(ModBlocks.WEATHERED_LIMESTONE.getKey())
                .add(ModBlocks.MARBLE.getKey())
                .add(ModBlocks.MARBLE_BRICKS.getKey())

                .add(ModBlocks.KEYSTONE_ORE.getKey())
                .add(ModBlocks.GREEK_STEEL_ORE.getKey())
                .add(ModBlocks.SUNSTONE_ORE.getKey())
                .add(ModBlocks.AETHER_ORE.getKey())
                .add(ModBlocks.ORICHALCUM_ORE.getKey())
                .add(ModBlocks.STYGIAN_ORE.getKey());


        /*
         * ============================================================
         * VANILLA MINING REQUIREMENTS
         * ============================================================
         */


        /*
         * DIAMOND LEVEL
         *
         * Diamond is the entry point into the Greek progression.
         */
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.KEYSTONE_ORE.getKey())
                .add(ModBlocks.GREEK_PORTAL_FRAME.getKey())
                .add(ModBlocks.GREEK_STEEL_ORE.getKey());


        /*
         * NETHERITE LEVEL
         *
         * Zenith is above Netherite.
         *
         * Both Zenith ore and the Zenith storage block
         * require Netherite or something capable of mining
         * Netherite-level blocks.
         */
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.ZENITH_END_ORE.getKey())
                .add(ModBlocks.ZENITH_BLOCK.getKey());


        /*
         * ============================================================
         * CUSTOM GREEK MINING REQUIREMENTS
         * ============================================================
         */


        /*
         * Greek Steel Pickaxe
         *
         * Can unlock:
         * - Aether
         * - Sunstone
         */
        this.tag(ModTags.Blocks.NEEDS_GREEK_STEEL_TOOL)
                .add(ModBlocks.AETHER_ORE.getKey())
                .add(ModBlocks.SUNSTONE_ORE.getKey());


        /*
         * Aether Pickaxe
         *
         * Can unlock:
         * - Orichalcum
         */
        this.tag(ModTags.Blocks.NEEDS_AETHER_TOOL)
                .add(ModBlocks.ORICHALCUM_ORE.getKey());


        /*
         * Orichalcum Pickaxe
         *
         * Can unlock:
         * - Stygian
         */
        this.tag(ModTags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .add(ModBlocks.STYGIAN_ORE.getKey());


        /*
         * Nothing currently requires Stygian.
         *
         * Leave this generated so it is ready for
         * a future material above Stygian.
         */
        this.tag(ModTags.Blocks.NEEDS_STYGIAN_TOOL);


        /*
         * Nothing currently requires Zenith.
         *
         * Zenith is currently the highest overall tool tier.
         *
         * If you eventually add something above Zenith,
         * put those blocks in this tag.
         */
        this.tag(ModTags.Blocks.NEEDS_ZENITH_TOOL);


        /*
         * ============================================================
         * BUILD CUSTOM TOOL HIERARCHY
         * ============================================================
         */

        buildCustomToolHierarchy();


        /*
         * ============================================================
         * PREVENT VANILLA TOOLS FROM SKIPPING THE GREEK PROGRESSION
         * ============================================================
         *
         * Without this, Diamond or Netherite would not automatically
         * understand what NEEDS_AETHER_TOOL, NEEDS_ORICHALCUM_TOOL,
         * etc. mean.
         */

        addCustomRequirementsToVanillaTools();


        /*
         * ============================================================
         * PREVENT LOWER TOOLS FROM MINING ZENITH
         * ============================================================
         *
         * Zenith requires Netherite.
         *
         * These tools should NOT successfully harvest it:
         *
         * Wood
         * Stone
         * Iron
         * Gold
         * Diamond
         */

        addRequirementToIncorrectTags(
                Tags.Blocks.NEEDS_NETHERITE_TOOL,

                BlockTags.INCORRECT_FOR_WOODEN_TOOL,
                BlockTags.INCORRECT_FOR_STONE_TOOL,
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                BlockTags.INCORRECT_FOR_GOLD_TOOL,
                BlockTags.INCORRECT_FOR_DIAMOND_TOOL
        );


        /*
         * ============================================================
         * PREVENT GREEK TOOLS FROM BYPASSING NETHERITE -> ZENITH
         * ============================================================
         *
         * Greek Steel, Aether, Orichalcum, and Stygian
         * are NOT allowed to mine Zenith.
         *
         * You must specifically obtain Netherite first.
         */

        for (TagKey<Block> customIncorrect : CUSTOM_INCORRECT_TAGS) {

            this.tag(customIncorrect)
                    .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL);
        }


        /*
         * ============================================================
         * ZENITH
         * ============================================================
         *
         * Zenith is currently the highest tool level.
         *
         * Its incorrect tag is empty, meaning it can mine
         * everything currently in the mod.
         *
         * If you add a tier ABOVE Zenith later, add that
         * requirement tag here.
         */

        this.tag(ModTags.Blocks.INCORRECT_FOR_ZENITH_TOOL);


        /*
         * ============================================================
         * DIRT TAGS
         * ============================================================
         */

        this.tag(BlockTags.DIRT)
                .add(ModBlocks.ANCIENT_DIRT.getKey())
                .add(ModBlocks.ANCIENT_GRASS_BLOCK.getKey());


        /*
         * ============================================================
         * SHOVEL-MINEABLE BLOCKS
         * ============================================================
         */

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.ANCIENT_DIRT.getKey())
                .add(ModBlocks.SUNBAKED_DIRT.getKey())
                .add(ModBlocks.ANCIENT_GRASS_BLOCK.getKey())
                .add(ModBlocks.SUNBAKED_GRASS_BLOCK.getKey());
    }


    /*
     * ================================================================
     * CUSTOM HIERARCHY BUILDER
     * ================================================================
     *
     * Automatically generates:
     *
     * Greek Steel cannot mine:
     *      Aether requirement
     *      Orichalcum requirement
     *      Stygian requirement
     *
     * Aether cannot mine:
     *      Orichalcum requirement
     *      Stygian requirement
     *
     * Orichalcum cannot mine:
     *      Stygian requirement
     *
     * Stygian:
     *      Can mine everything in the Greek progression
     *
     *
     * This is why adding another tier later is easy.
     */

    private void buildCustomToolHierarchy() {

        /*
         * Safety check.
         *
         * Both lists must always have the same number
         * of elements.
         */
        if (CUSTOM_NEEDS_TAGS.size() != CUSTOM_INCORRECT_TAGS.size()) {

            throw new IllegalStateException(
                    "Custom mining hierarchy lists must be the same size!"
            );
        }


        /*
         * Loop through every custom tool.
         */
        for (
                int toolIndex = 0;
                toolIndex < CUSTOM_INCORRECT_TAGS.size();
                toolIndex++
        ) {

            TagKey<Block> incorrectTag =
                    CUSTOM_INCORRECT_TAGS.get(toolIndex);


            var builder = this.tag(incorrectTag);


            /*
             * Start one level ABOVE the current tool.
             *
             * Example:
             *
             * Greek Steel = index 0
             *
             * Start at index 1:
             *
             * NEEDS_AETHER
             * NEEDS_ORICHALCUM
             * NEEDS_STYGIAN
             *
             * Therefore Greek Steel cannot mine those.
             */
            for (
                    int requirementIndex = toolIndex + 1;
                    requirementIndex < CUSTOM_NEEDS_TAGS.size();
                    requirementIndex++
            ) {

                builder.addTag(
                        CUSTOM_NEEDS_TAGS.get(requirementIndex)
                );
            }
        }
    }


    /*
     * ================================================================
     * VANILLA TOOL PROTECTION
     * ================================================================
     *
     * Vanilla tools do not automatically understand our
     * custom NEEDS_X_TOOL tags.
     *
     * Therefore:
     *
     * Wood
     * Stone
     * Iron
     * Gold
     * Diamond
     * Netherite
     *
     * cannot skip directly into Aether, Orichalcum, or Stygian.
     */

    private void addCustomRequirementsToVanillaTools() {

        List<TagKey<Block>> vanillaIncorrectTags = List.of(
                BlockTags.INCORRECT_FOR_WOODEN_TOOL,
                BlockTags.INCORRECT_FOR_STONE_TOOL,
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                BlockTags.INCORRECT_FOR_GOLD_TOOL,
                BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                BlockTags.INCORRECT_FOR_NETHERITE_TOOL
        );


        /*
         * Take every custom requirement...
         */
        for (TagKey<Block> requirement : CUSTOM_NEEDS_TAGS) {


            /*
             * ...and tell every vanilla tool that those
             * blocks are incorrect for it.
             */
            for (TagKey<Block> vanillaIncorrect : vanillaIncorrectTags) {

                this.tag(vanillaIncorrect)
                        .addTag(requirement);
            }
        }
    }


    /*
     * ================================================================
     * REQUIREMENT HELPER
     * ================================================================
     *
     * Adds one mining requirement to several
     * INCORRECT_FOR tags.
     */

    @SafeVarargs
    private final void addRequirementToIncorrectTags(
            TagKey<Block> requirement,
            TagKey<Block>... incorrectTags
    ) {

        for (TagKey<Block> incorrectTag : incorrectTags) {

            this.tag(incorrectTag)
                    .addTag(requirement);
        }
    }
}
