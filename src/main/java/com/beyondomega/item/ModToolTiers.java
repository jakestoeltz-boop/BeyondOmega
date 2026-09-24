package com.beyondomega.item;

import com.beyondomega.tags.ModTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolTiers {

    /*
     * ============================================================
     * TOOL PROGRESSION
     * ============================================================
     *
     * Greek progression:
     *
     * Diamond
     *   ↓
     * Greek Steel
     *   ↓
     * Aether
     *   ↓
     * Orichalcum
     *   ↓
     * Stygian
     *
     *
     * Zenith progression:
     *
     * Netherite
     *   ↓
     * Zenith
     *
     *
     * ToolMaterial arguments:
     *
     * 1. Incorrect-for-tool block tag
     * 2. Durability
     * 3. Mining speed
     * 4. Attack damage bonus
     * 5. Enchantability
     * 6. Repair material tag
     */


    /*
     * ============================================================
     * GREEK STEEL
     * ============================================================
     *
     * First custom progression tier.
     *
     * Slightly better than Diamond.
     */

    public static final ToolMaterial GREEK_STEEL =
            new ToolMaterial(
                    ModTags.Blocks.INCORRECT_FOR_GREEK_STEEL_TOOL,

                    1800,   // Durability
                    8.5f,   // Mining speed
                    3.5f,   // Attack damage bonus
                    12,     // Enchantability

                    ModTags.Items.GREEK_STEEL_REPAIRABLE
            );


    /*
     * ============================================================
     * AETHER
     * ============================================================
     *
     * Fast, magical tier.
     *
     * Big mining-speed improvement and high enchantability.
     */

    public static final ToolMaterial AETHER =
            new ToolMaterial(
                    ModTags.Blocks.INCORRECT_FOR_AETHER_TOOL,

                    2200,   // Durability
                    10.0f,  // Mining speed
                    4.0f,   // Attack damage bonus
                    18,     // Enchantability

                    ModTags.Items.AETHER_REPAIRABLE
            );


    /*
     * ============================================================
     * ORICHALCUM
     * ============================================================
     *
     * Stronger and substantially more durable than Aether.
     */

    public static final ToolMaterial ORICHALCUM =
            new ToolMaterial(
                    ModTags.Blocks.INCORRECT_FOR_ORICHALCUM_TOOL,

                    2800,   // Durability
                    10.5f,  // Mining speed
                    4.5f,   // Attack damage bonus
                    16,     // Enchantability

                    ModTags.Items.ORICHALCUM_REPAIRABLE
            );


    /*
     * ============================================================
     * STYGIAN
     * ============================================================
     *
     * Extremely durable and powerful.
     *
     * This is the peak of the Greek material progression.
     */

    public static final ToolMaterial STYGIAN =
            new ToolMaterial(
                    ModTags.Blocks.INCORRECT_FOR_STYGIAN_TOOL,

                    3400,   // Durability
                    11.0f,  // Mining speed
                    5.0f,   // Attack damage bonus
                    14,     // Enchantability

                    ModTags.Items.STYGIAN_REPAIRABLE
            );


    /*
     * ============================================================
     * ZENITH
     * ============================================================
     *
     * Ultimate overall material.
     *
     * Highest:
     *
     * - durability
     * - mining speed
     * - base damage
     * - enchantability
     */

    public static final ToolMaterial ZENITH =
            new ToolMaterial(
                    ModTags.Blocks.INCORRECT_FOR_ZENITH_TOOL,

                    4200,   // Durability
                    12.0f,  // Mining speed
                    5.5f,   // Attack damage bonus
                    22,     // Enchantability

                    ModTags.Items.ZENITH_REPAIRABLE
            );
}
