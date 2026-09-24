package com.beyondomega.item;

import com.beyondomega.BeyondOmega;

import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(BeyondOmega.MOD_ID);


    /*
     * ============================================================
     * STANDARD TOOL VALUES
     * ============================================================
     *
     * Keep these consistent between materials.
     *
     * Material progression comes from ModToolTiers.
     */


    // Sword
    private static final float SWORD_DAMAGE = 3.0f;
    private static final float SWORD_SPEED = -2.4f;


    // Pickaxe
    private static final float PICKAXE_DAMAGE = 1.0f;
    private static final float PICKAXE_SPEED = -2.8f;


    // Shovel
    private static final float SHOVEL_DAMAGE = 1.5f;
    private static final float SHOVEL_SPEED = -3.0f;


    // Axe
    private static final float AXE_DAMAGE = 5.0f;
    private static final float AXE_SPEED = -3.1f;


    // Hoe
    private static final float HOE_DAMAGE = 0.0f;
    private static final float HOE_SPEED = -3.0f;


    /*
     * The Labyrinth Battle Axe is a special boss weapon.
     *
     * Higher burst damage than a normal axe,
     * but slower attack speed.
     */
    private static final float BATTLE_AXE_DAMAGE = 8.0f;
    private static final float BATTLE_AXE_SPEED = -3.3f;



    /*
     * ============================================================
     * BASIC MATERIALS / INGREDIENTS
     * ============================================================
     */

    public static final DeferredItem<Item> RIFTSTONE_SHARD =
            ITEMS.registerSimpleItem("riftstone_shard");

    public static final DeferredItem<Item> UMBRAL_SHARD =
            ITEMS.registerSimpleItem("umbral_shard");

    public static final DeferredItem<Item> ZENITH_CORE =
            ITEMS.registerSimpleItem("zenith_core");


    public static final DeferredItem<Item> KEYSTONE_SHARD =
            ITEMS.registerSimpleItem("keystone_shard");

    public static final DeferredItem<Item> KEYSTONE_FRAGMENT =
            ITEMS.registerSimpleItem("keystone_fragment");

    public static final DeferredItem<Item> KEYSTONE_PORTAL_CORE =
            ITEMS.registerSimpleItem("keystone_portal_core");


    public static final DeferredItem<Item> RAW_GREEK_STEEL =
            ITEMS.registerSimpleItem("raw_greek_steel");

    public static final DeferredItem<Item> GREEK_STEEL_INGOT =
            ITEMS.registerSimpleItem("greek_steel_ingot");


    public static final DeferredItem<Item> AETHER_CRYSTAL =
            ITEMS.registerSimpleItem("aether_crystal");

    public static final DeferredItem<Item> SUNSTONE =
            ITEMS.registerSimpleItem("sunstone");


    public static final DeferredItem<Item> RAW_ORICHALCUM =
            ITEMS.registerSimpleItem("raw_orichalcum");

    public static final DeferredItem<Item> ORICHALCUM_INGOT =
            ITEMS.registerSimpleItem("orichalcum_ingot");


    public static final DeferredItem<Item> RAW_STYGIAN =
            ITEMS.registerSimpleItem("raw_stygian");

    public static final DeferredItem<Item> STYGIAN_INGOT =
            ITEMS.registerSimpleItem("stygian_ingot");


    public static final DeferredItem<Item> MINOTAUR_HORN =
            ITEMS.registerSimpleItem("minotaur_horn");



    /*
     * ============================================================
     * GREEK STEEL TOOLS
     * ============================================================
     */

    public static final DeferredItem<Item> GREEK_STEEL_SWORD =
            ITEMS.registerItem(
                    "greek_steel_sword",
                    properties -> new Item(
                            properties.sword(
                                    ModToolTiers.GREEK_STEEL,
                                    SWORD_DAMAGE,
                                    SWORD_SPEED
                            )
                    )
            );


    public static final DeferredItem<Item> GREEK_STEEL_PICKAXE =
            ITEMS.registerItem(
                    "greek_steel_pickaxe",
                    properties -> new Item(
                            properties.pickaxe(
                                    ModToolTiers.GREEK_STEEL,
                                    PICKAXE_DAMAGE,
                                    PICKAXE_SPEED
                            )
                    )
            );


    public static final DeferredItem<Item> GREEK_STEEL_SHOVEL =
            ITEMS.registerItem(
                    "greek_steel_shovel",
                    properties -> new ShovelItem(
                            ModToolTiers.GREEK_STEEL,
                            SHOVEL_DAMAGE,
                            SHOVEL_SPEED,
                            properties
                    )
            );


    public static final DeferredItem<Item> GREEK_STEEL_AXE =
            ITEMS.registerItem(
                    "greek_steel_axe",
                    properties -> new AxeItem(
                            ModToolTiers.GREEK_STEEL,
                            AXE_DAMAGE,
                            AXE_SPEED,
                            properties
                    )
            );


    public static final DeferredItem<Item> GREEK_STEEL_HOE =
            ITEMS.registerItem(
                    "greek_steel_hoe",
                    properties -> new HoeItem(
                            ModToolTiers.GREEK_STEEL,
                            HOE_DAMAGE,
                            HOE_SPEED,
                            properties
                    )
            );



    /*
     * ============================================================
     * AETHER TOOLS
     * ============================================================
     */

    public static final DeferredItem<Item> AETHER_SWORD =
            ITEMS.registerItem(
                    "aether_sword",
                    properties -> new Item(
                            properties.sword(
                                    ModToolTiers.AETHER,
                                    SWORD_DAMAGE,
                                    SWORD_SPEED
                            )
                    )
            );


    public static final DeferredItem<Item> AETHER_PICKAXE =
            ITEMS.registerItem(
                    "aether_pickaxe",
                    properties -> new Item(
                            properties.pickaxe(
                                    ModToolTiers.AETHER,
                                    PICKAXE_DAMAGE,
                                    PICKAXE_SPEED
                            )
                    )
            );


    public static final DeferredItem<Item> AETHER_SHOVEL =
            ITEMS.registerItem(
                    "aether_shovel",
                    properties -> new ShovelItem(
                            ModToolTiers.AETHER,
                            SHOVEL_DAMAGE,
                            SHOVEL_SPEED,
                            properties
                    )
            );


    public static final DeferredItem<Item> AETHER_AXE =
            ITEMS.registerItem(
                    "aether_axe",
                    properties -> new AxeItem(
                            ModToolTiers.AETHER,
                            AXE_DAMAGE,
                            AXE_SPEED,
                            properties
                    )
            );


    public static final DeferredItem<Item> AETHER_HOE =
            ITEMS.registerItem(
                    "aether_hoe",
                    properties -> new HoeItem(
                            ModToolTiers.AETHER,
                            HOE_DAMAGE,
                            HOE_SPEED,
                            properties
                    )
            );



    /*
     * ============================================================
     * ORICHALCUM TOOLS
     * ============================================================
     */

    public static final DeferredItem<Item> ORICHALCUM_SWORD =
            ITEMS.registerItem(
                    "orichalcum_sword",
                    properties -> new Item(
                            properties.sword(
                                    ModToolTiers.ORICHALCUM,
                                    SWORD_DAMAGE,
                                    SWORD_SPEED
                            )
                    )
            );


    public static final DeferredItem<Item> ORICHALCUM_PICKAXE =
            ITEMS.registerItem(
                    "orichalcum_pickaxe",
                    properties -> new Item(
                            properties.pickaxe(
                                    ModToolTiers.ORICHALCUM,
                                    PICKAXE_DAMAGE,
                                    PICKAXE_SPEED
                            )
                    )
            );


    public static final DeferredItem<Item> ORICHALCUM_SHOVEL =
            ITEMS.registerItem(
                    "orichalcum_shovel",
                    properties -> new ShovelItem(
                            ModToolTiers.ORICHALCUM,
                            SHOVEL_DAMAGE,
                            SHOVEL_SPEED,
                            properties
                    )
            );


    public static final DeferredItem<Item> ORICHALCUM_AXE =
            ITEMS.registerItem(
                    "orichalcum_axe",
                    properties -> new AxeItem(
                            ModToolTiers.ORICHALCUM,
                            AXE_DAMAGE,
                            AXE_SPEED,
                            properties
                    )
            );


    public static final DeferredItem<Item> ORICHALCUM_HOE =
            ITEMS.registerItem(
                    "orichalcum_hoe",
                    properties -> new HoeItem(
                            ModToolTiers.ORICHALCUM,
                            HOE_DAMAGE,
                            HOE_SPEED,
                            properties
                    )
            );



    /*
     * ============================================================
     * STYGIAN TOOLS
     * ============================================================
     */

    public static final DeferredItem<Item> STYGIAN_SWORD =
            ITEMS.registerItem(
                    "stygian_sword",
                    properties -> new Item(
                            properties.sword(
                                    ModToolTiers.STYGIAN,
                                    SWORD_DAMAGE,
                                    SWORD_SPEED
                            )
                    )
            );


    public static final DeferredItem<Item> STYGIAN_PICKAXE =
            ITEMS.registerItem(
                    "stygian_pickaxe",
                    properties -> new Item(
                            properties.pickaxe(
                                    ModToolTiers.STYGIAN,
                                    PICKAXE_DAMAGE,
                                    PICKAXE_SPEED
                            )
                    )
            );


    public static final DeferredItem<Item> STYGIAN_SHOVEL =
            ITEMS.registerItem(
                    "stygian_shovel",
                    properties -> new ShovelItem(
                            ModToolTiers.STYGIAN,
                            SHOVEL_DAMAGE,
                            SHOVEL_SPEED,
                            properties
                    )
            );


    public static final DeferredItem<Item> STYGIAN_AXE =
            ITEMS.registerItem(
                    "stygian_axe",
                    properties -> new AxeItem(
                            ModToolTiers.STYGIAN,
                            AXE_DAMAGE,
                            AXE_SPEED,
                            properties
                    )
            );


    public static final DeferredItem<Item> STYGIAN_HOE =
            ITEMS.registerItem(
                    "stygian_hoe",
                    properties -> new HoeItem(
                            ModToolTiers.STYGIAN,
                            HOE_DAMAGE,
                            HOE_SPEED,
                            properties
                    )
            );



    /*
     * ============================================================
     * LABYRINTH BATTLE AXE
     * ============================================================
     *
     * Uses Stygian material stats.
     *
     * More damage than a normal Stygian axe but attacks slower.
     */

    public static final DeferredItem<Item> LABYRINTH_BATTLE_AXE =
            ITEMS.registerItem(
                    "labyrinth_battle_axe",
                    properties -> new AxeItem(
                            ModToolTiers.STYGIAN,
                            BATTLE_AXE_DAMAGE,
                            BATTLE_AXE_SPEED,
                            properties
                                    .rarity(Rarity.EPIC)
                    )
            );



    /*
     * ============================================================
     * ZENITH TOOLS
     * ============================================================
     *
     * Zenith is the ultimate material.
     *
     * All Zenith equipment is also fire resistant.
     */

    public static final DeferredItem<Item> ZENITH_SWORD =
            ITEMS.registerItem(
                    "zenith_sword",
                    properties -> new Item(
                            properties
                                    .sword(
                                            ModToolTiers.ZENITH,
                                            SWORD_DAMAGE,
                                            SWORD_SPEED
                                    )
                                    .fireResistant()
                    )
            );


    public static final DeferredItem<Item> ZENITH_PICKAXE =
            ITEMS.registerItem(
                    "zenith_pickaxe",
                    properties -> new Item(
                            properties
                                    .pickaxe(
                                            ModToolTiers.ZENITH,
                                            PICKAXE_DAMAGE,
                                            PICKAXE_SPEED
                                    )
                                    .fireResistant()
                    )
            );


    public static final DeferredItem<Item> ZENITH_SHOVEL =
            ITEMS.registerItem(
                    "zenith_shovel",
                    properties -> new ShovelItem(
                            ModToolTiers.ZENITH,
                            SHOVEL_DAMAGE,
                            SHOVEL_SPEED,
                            properties.fireResistant()
                    )
            );


    public static final DeferredItem<Item> ZENITH_AXE =
            ITEMS.registerItem(
                    "zenith_axe",
                    properties -> new AxeItem(
                            ModToolTiers.ZENITH,
                            AXE_DAMAGE,
                            AXE_SPEED,
                            properties.fireResistant()
                    )
            );


    public static final DeferredItem<Item> ZENITH_HOE =
            ITEMS.registerItem(
                    "zenith_hoe",
                    properties -> new HoeItem(
                            ModToolTiers.ZENITH,
                            HOE_DAMAGE,
                            HOE_SPEED,
                            properties.fireResistant()
                    )
            );



    /*
     * ============================================================
     * ZENITH BOW
     * ============================================================
     *
     * NOTE:
     *
     * ToolMaterial does NOT affect bow projectile damage.
     *
     * This currently gives the Zenith Bow:
     *
     * - greater durability
     * - Epic rarity
     * - fire resistance
     *
     * A custom bow class is needed if you want it to actually
     * shoot harder/faster than a vanilla bow.
     */

    public static final DeferredItem<Item> ZENITH_BOW =
            ITEMS.registerItem(
                    "zenith_bow",
                    properties -> new BowItem(
                            properties
                                    .durability(750)
                                    .rarity(Rarity.EPIC)
                                    .fireResistant()
                    )
            );



    /*
     * ============================================================
     * ZENITH ARMOR
     * ============================================================
     */

    public static final DeferredItem<Item> ZENITH_HELMET =
            ITEMS.registerItem(
                    "zenith_helmet",
                    properties -> new Item(
                            properties
                                    .humanoidArmor(
                                            ModArmorMaterials.ZENITH_ARMOR_MATERIAL,
                                            ArmorType.HELMET
                                    )
                                    .fireResistant()
                    )
            );


    public static final DeferredItem<Item> ZENITH_CHESTPLATE =
            ITEMS.registerItem(
                    "zenith_chestplate",
                    properties -> new Item(
                            properties
                                    .humanoidArmor(
                                            ModArmorMaterials.ZENITH_ARMOR_MATERIAL,
                                            ArmorType.CHESTPLATE
                                    )
                                    .fireResistant()
                    )
            );


    public static final DeferredItem<Item> ZENITH_LEGGINGS =
            ITEMS.registerItem(
                    "zenith_leggings",
                    properties -> new Item(
                            properties
                                    .humanoidArmor(
                                            ModArmorMaterials.ZENITH_ARMOR_MATERIAL,
                                            ArmorType.LEGGINGS
                                    )
                                    .fireResistant()
                    )
            );


    public static final DeferredItem<Item> ZENITH_BOOTS =
            ITEMS.registerItem(
                    "zenith_boots",
                    properties -> new Item(
                            properties
                                    .humanoidArmor(
                                            ModArmorMaterials.ZENITH_ARMOR_MATERIAL,
                                            ArmorType.BOOTS
                                    )
                                    .fireResistant()
                    )
            );



    /*
     * ============================================================
     * ADVANCEMENT / TAB ICONS
     * ============================================================
     */

    public static final DeferredItem<Item> BEYOND_OMEGA_TAB_ICON =
            ITEMS.registerSimpleItem("beyond_omega_tab_icon");

    public static final DeferredItem<Item> MINOTAUR_ACHIEVEMENT_ICON =
            ITEMS.registerSimpleItem("minotaur_achievement_icon");



    /*
     * ============================================================
     * REGISTRATION
     * ============================================================
     */

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}