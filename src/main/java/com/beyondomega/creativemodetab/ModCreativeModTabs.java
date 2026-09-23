package com.beyondomega.creativemodetab;

import com.beyondomega.BeyondOmega;
import com.beyondomega.block.ModBlocks;
import com.beyondomega.item.ModItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(
                    Registries.CREATIVE_MODE_TAB,
                    BeyondOmega.MOD_ID
            );


    /*
     * ============================================================
     * NATURALLY GENERATING BLOCKS
     * ============================================================
     *
     * Terrain
     * Natural stone
     * Ores
     *
     * Ordered roughly by player progression.
     */

    public static final Supplier<CreativeModeTab> NATURAL_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register(
                    "natural_blocks_tab",
                    () -> CreativeModeTab.builder()

                            .icon(() ->
                                    new ItemStack(
                                            ModBlocks.GREEK_STEEL_ORE.get()
                                    )
                            )

                            .title(
                                    Component.translatable(
                                            "creativetab.beyondomega.natural_blocks"
                                    )
                            )

                            .withTabsBefore(
                                    CreativeModeTabs.INGREDIENTS
                            )

                            .displayItems(
                                    (itemDisplayParameters, output) -> {

                                        /*
                                         * ----------------------------
                                         * TERRAIN / NATURAL BLOCKS
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModBlocks.LIMESTONE
                                        );

                                        output.accept(
                                                ModBlocks.WEATHERED_LIMESTONE
                                        );

                                        output.accept(
                                                ModBlocks.MARBLE
                                        );

                                        output.accept(
                                                ModBlocks.ANCIENT_DIRT
                                        );

                                        output.accept(
                                                ModBlocks.ANCIENT_GRASS_BLOCK
                                        );

                                        output.accept(
                                                ModBlocks.SUNBAKED_DIRT
                                        );

                                        output.accept(
                                                ModBlocks.SUNBAKED_GRASS_BLOCK
                                        );


                                        /*
                                         * ----------------------------
                                         * ORES / PROGRESSION
                                         * ----------------------------
                                         */

                                        // Keystone
                                        output.accept(
                                                ModBlocks.KEYSTONE_ORE
                                        );


                                        // Greek Steel
                                        output.accept(
                                                ModBlocks.GREEK_STEEL_ORE
                                        );


                                        // Greek Steel unlocks these
                                        output.accept(
                                                ModBlocks.AETHER_ORE
                                        );

                                        output.accept(
                                                ModBlocks.SUNSTONE_ORE
                                        );


                                        // Aether unlocks Orichalcum
                                        output.accept(
                                                ModBlocks.ORICHALCUM_ORE
                                        );


                                        // Orichalcum unlocks Stygian
                                        output.accept(
                                                ModBlocks.STYGIAN_ORE
                                        );


                                        // Netherite branch -> Zenith
                                        output.accept(
                                                ModBlocks.ZENITH_END_ORE
                                        );
                                    }
                            )

                            .build()
            );


    /*
     * ============================================================
     * CRAFTED BLOCKS
     * ============================================================
     *
     * Blocks created by the player rather than naturally generated.
     */

    public static final Supplier<CreativeModeTab> CRAFTED_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register(
                    "crafted_blocks_tab",
                    () -> CreativeModeTab.builder()

                            .icon(() ->
                                    new ItemStack(
                                            ModBlocks.MARBLE_BRICKS.get()
                                    )
                            )

                            .title(
                                    Component.translatable(
                                            "creativetab.beyondomega.crafted_blocks"
                                    )
                            )

                            .withTabsAfter(
                                    Identifier.fromNamespaceAndPath(
                                            BeyondOmega.MOD_ID,
                                            "natural_blocks_tab"
                                    )
                            )

                            .displayItems(
                                    (itemDisplayParameters, output) -> {

                                        /*
                                         * ----------------------------
                                         * BUILDING BLOCKS
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModBlocks.MARBLE_BRICKS
                                        );


                                        /*
                                         * ----------------------------
                                         * FUNCTIONAL / SPECIAL BLOCKS
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModBlocks.GREEK_PORTAL_FRAME
                                        );


                                        /*
                                         * ----------------------------
                                         * STORAGE BLOCKS
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModBlocks.ZENITH_BLOCK
                                        );
                                    }
                            )

                            .build()
            );


    /*
     * ============================================================
     * INGREDIENTS
     * ============================================================
     *
     * Raw materials, shards, ingots, crystals, boss drops, etc.
     *
     * Ordered by progression.
     */

    public static final Supplier<CreativeModeTab> INGREDIENTS_TAB =
            CREATIVE_MODE_TABS.register(
                    "ingredients_tab",
                    () -> CreativeModeTab.builder()

                            .icon(() ->
                                    new ItemStack(
                                            ModItems.GREEK_STEEL_INGOT.get()
                                    )
                            )

                            .title(
                                    Component.translatable(
                                            "creativetab.beyondomega.ingredients"
                                    )
                            )

                            .withTabsAfter(
                                    Identifier.fromNamespaceAndPath(
                                            BeyondOmega.MOD_ID,
                                            "crafted_blocks_tab"
                                    )
                            )

                            .displayItems(
                                    (itemDisplayParameters, output) -> {

                                        /*
                                         * ----------------------------
                                         * RIFT / PORTAL MATERIALS
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.RIFTSTONE_SHARD
                                        );

                                        output.accept(
                                                ModItems.UMBRAL_SHARD
                                        );

                                        output.accept(
                                                ModItems.KEYSTONE_FRAGMENT
                                        );

                                        output.accept(
                                                ModItems.KEYSTONE_SHARD
                                        );

                                        output.accept(
                                                ModItems.KEYSTONE_PORTAL_CORE
                                        );


                                        /*
                                         * ----------------------------
                                         * GREEK STEEL
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.RAW_GREEK_STEEL
                                        );

                                        output.accept(
                                                ModItems.GREEK_STEEL_INGOT
                                        );


                                        /*
                                         * ----------------------------
                                         * AETHER / SUNSTONE
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.AETHER_CRYSTAL
                                        );

                                        output.accept(
                                                ModItems.SUNSTONE
                                        );


                                        /*
                                         * ----------------------------
                                         * ORICHALCUM
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.RAW_ORICHALCUM
                                        );

                                        output.accept(
                                                ModItems.ORICHALCUM_INGOT
                                        );


                                        /*
                                         * ----------------------------
                                         * STYGIAN
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.RAW_STYGIAN
                                        );

                                        output.accept(
                                                ModItems.STYGIAN_INGOT
                                        );


                                        /*
                                         * ----------------------------
                                         * SPECIAL / BOSS MATERIALS
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.MINOTAUR_HORN
                                        );


                                        /*
                                         * ----------------------------
                                         * ZENITH
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.ZENITH_CORE
                                        );
                                    }
                            )

                            .build()
            );


    /*
     * ============================================================
     * TOOLS
     * ============================================================
     *
     * Pickaxes
     * Axes
     * Shovels
     * Hoes
     *
     * Weapons are intentionally NOT in this tab.
     */

    public static final Supplier<CreativeModeTab> TOOLS_TAB =
            CREATIVE_MODE_TABS.register(
                    "tools_tab",
                    () -> CreativeModeTab.builder()

                            .icon(() ->
                                    new ItemStack(
                                            ModItems.GREEK_STEEL_PICKAXE.get()
                                    )
                            )

                            .title(
                                    Component.translatable(
                                            "creativetab.beyondomega.tools"
                                    )
                            )

                            .withTabsAfter(
                                    Identifier.fromNamespaceAndPath(
                                            BeyondOmega.MOD_ID,
                                            "ingredients_tab"
                                    )
                            )

                            .displayItems(
                                    (itemDisplayParameters, output) -> {

                                        /*
                                         * ----------------------------
                                         * GREEK STEEL
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.GREEK_STEEL_PICKAXE
                                        );

                                        output.accept(
                                                ModItems.GREEK_STEEL_AXE
                                        );

                                        output.accept(
                                                ModItems.GREEK_STEEL_SHOVEL
                                        );

                                        output.accept(
                                                ModItems.GREEK_STEEL_HOE
                                        );


                                        /*
                                         * ----------------------------
                                         * AETHER
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.AETHER_PICKAXE
                                        );

                                        output.accept(
                                                ModItems.AETHER_AXE
                                        );

                                        output.accept(
                                                ModItems.AETHER_SHOVEL
                                        );

                                        output.accept(
                                                ModItems.AETHER_HOE
                                        );


                                        /*
                                         * ----------------------------
                                         * ORICHALCUM
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.ORICHALCUM_PICKAXE
                                        );

                                        output.accept(
                                                ModItems.ORICHALCUM_AXE
                                        );

                                        output.accept(
                                                ModItems.ORICHALCUM_SHOVEL
                                        );

                                        output.accept(
                                                ModItems.ORICHALCUM_HOE
                                        );


                                        /*
                                         * ----------------------------
                                         * STYGIAN
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.STYGIAN_PICKAXE
                                        );

                                        output.accept(
                                                ModItems.STYGIAN_AXE
                                        );

                                        output.accept(
                                                ModItems.STYGIAN_SHOVEL
                                        );

                                        output.accept(
                                                ModItems.STYGIAN_HOE
                                        );


                                        /*
                                         * ----------------------------
                                         * ZENITH
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.ZENITH_PICKAXE
                                        );

                                        output.accept(
                                                ModItems.ZENITH_AXE
                                        );

                                        output.accept(
                                                ModItems.ZENITH_SHOVEL
                                        );

                                        output.accept(
                                                ModItems.ZENITH_HOE
                                        );
                                    }
                            )

                            .build()
            );


    /*
     * ============================================================
     * WEAPONS & ARMOR
     * ============================================================
     *
     * Swords
     * Bows
     * Special weapons
     * Armor
     *
     * Ordered by progression.
     */

    public static final Supplier<CreativeModeTab> WEAPONS_ARMOR_TAB =
            CREATIVE_MODE_TABS.register(
                    "weapons_armor_tab",
                    () -> CreativeModeTab.builder()

                            .icon(() ->
                                    new ItemStack(
                                            ModItems.LABYRINTH_BATTLE_AXE.get()
                                    )
                            )

                            .title(
                                    Component.translatable(
                                            "creativetab.beyondomega.weapons_armor"
                                    )
                            )

                            .withTabsAfter(
                                    Identifier.fromNamespaceAndPath(
                                            BeyondOmega.MOD_ID,
                                            "tools_tab"
                                    )
                            )

                            .displayItems(
                                    (itemDisplayParameters, output) -> {

                                        /*
                                         * ----------------------------
                                         * GREEK STEEL
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.GREEK_STEEL_SWORD
                                        );


                                        /*
                                         * ----------------------------
                                         * AETHER
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.AETHER_SWORD
                                        );


                                        /*
                                         * ----------------------------
                                         * ORICHALCUM
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.ORICHALCUM_SWORD
                                        );


                                        /*
                                         * ----------------------------
                                         * STYGIAN
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.STYGIAN_SWORD
                                        );


                                        /*
                                         * ----------------------------
                                         * SPECIAL / MINOTAUR
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.LABYRINTH_BATTLE_AXE
                                        );


                                        /*
                                         * ----------------------------
                                         * ZENITH WEAPONS
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.ZENITH_SWORD
                                        );

                                        output.accept(
                                                ModItems.ZENITH_BOW
                                        );


                                        /*
                                         * ----------------------------
                                         * ZENITH ARMOR
                                         * ----------------------------
                                         */

                                        output.accept(
                                                ModItems.ZENITH_HELMET
                                        );

                                        output.accept(
                                                ModItems.ZENITH_CHESTPLATE
                                        );

                                        output.accept(
                                                ModItems.ZENITH_LEGGINGS
                                        );

                                        output.accept(
                                                ModItems.ZENITH_BOOTS
                                        );
                                    }
                            )

                            .build()
            );


    /*
     * ============================================================
     * REGISTER
     * ============================================================
     */

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
