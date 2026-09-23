package com.beyondomega.item;

import com.beyondomega.BeyondOmega;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BeyondOmega.MOD_ID);

    public static final DeferredItem<Item> RIFTSTONE_SHARD = ITEMS.registerSimpleItem("riftstone_shard");
    public static final DeferredItem<Item> UMBRAL_SHARD = ITEMS.registerSimpleItem("umbral_shard");
    public static final DeferredItem<Item> ZENITH_CORE = ITEMS.registerSimpleItem("zenith_core");

    public static final DeferredItem<Item> KEYSTONE_SHARD = ITEMS.registerSimpleItem("keystone_shard");
    public static final DeferredItem<Item> KEYSTONE_FRAGMENT = ITEMS.registerSimpleItem("keystone_fragment");
    public static final DeferredItem<Item> KEYSTONE_PORTAL_CORE = ITEMS.registerSimpleItem("keystone_portal_core");

    public static final DeferredItem<Item> RAW_GREEK_STEEL = ITEMS.registerSimpleItem("raw_greek_steel");
    public static final DeferredItem<Item> RAW_ORICHALCUM = ITEMS.registerSimpleItem("raw_orichalcum");
    public static final DeferredItem<Item> RAW_STYGIAN = ITEMS.registerSimpleItem("raw_stygian");
    public static final DeferredItem<Item> GREEK_STEEL_INGOT = ITEMS.registerSimpleItem("greek_steel_ingot");
    public static final DeferredItem<Item> AETHER_CRYSTAL = ITEMS.registerSimpleItem("aether_crystal");
    public static final DeferredItem<Item> ORICHALCUM_INGOT = ITEMS.registerSimpleItem("orichalcum_ingot");
    public static final DeferredItem<Item> STYGIAN_INGOT = ITEMS.registerSimpleItem("stygian_ingot");
    public static final DeferredItem<Item> SUNSTONE = ITEMS.registerSimpleItem("sunstone");
    public static final DeferredItem<Item> MINOTAUR_HORN = ITEMS.registerSimpleItem("minotaur_horn");




    public static final DeferredItem<Item> GREEK_STEEL_SWORD = ITEMS.registerItem("greek_steel_sword",
            properties -> new Item(properties.sword(ModToolTiers.GREEK_STEEL, 3, -2.4f)));

    public static final DeferredItem<Item> GREEK_STEEL_PICKAXE = ITEMS.registerItem("greek_steel_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolTiers.GREEK_STEEL, 1, -2.8f)));

    public static final DeferredItem<Item> GREEK_STEEL_SHOVEL = ITEMS.registerItem("greek_steel_shovel",
            properties -> new ShovelItem(ModToolTiers.GREEK_STEEL, 1.5f, -3.0f, properties));

    public static final DeferredItem<Item> GREEK_STEEL_AXE = ITEMS.registerItem("greek_steel_axe",
            properties -> new Item(properties.axe(ModToolTiers.GREEK_STEEL, 5, -3.2f)));

    public static final DeferredItem<Item> GREEK_STEEL_HOE = ITEMS.registerItem("greek_steel_hoe",
            properties -> new HoeItem(ModToolTiers.GREEK_STEEL, 0, -3.0f, properties));


    public static final DeferredItem<Item> AETHER_SWORD = ITEMS.registerItem("aether_sword",
            properties -> new Item(properties.sword(ModToolTiers.AETHER, 3, -2.4f)));

    public static final DeferredItem<Item> AETHER_PICKAXE = ITEMS.registerItem("aether_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolTiers.AETHER, 1, -2.8f)));

    public static final DeferredItem<Item> AETHER_SHOVEL = ITEMS.registerItem("aether_shovel",
            properties -> new ShovelItem(ModToolTiers.AETHER, 1.5f, -3.0f, properties));

    public static final DeferredItem<Item> AETHER_AXE = ITEMS.registerItem("aether_axe",
            properties -> new Item(properties.axe(ModToolTiers.AETHER, 5, -3.2f)));

    public static final DeferredItem<Item> AETHER_HOE = ITEMS.registerItem("aether_hoe",
            properties -> new HoeItem(ModToolTiers.AETHER, 0, -3.0f, properties));


    public static final DeferredItem<Item> ORICHALCUM_SWORD = ITEMS.registerItem("orichalcum_sword",
            properties -> new Item(properties.sword(ModToolTiers.ORICHALCUM, 3, -2.4f)));

    public static final DeferredItem<Item> ORICHALCUM_PICKAXE = ITEMS.registerItem("orichalcum_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolTiers.ORICHALCUM, 1, -2.8f)));

    public static final DeferredItem<Item> ORICHALCUM_SHOVEL = ITEMS.registerItem("orichalcum_shovel",
            properties -> new ShovelItem(ModToolTiers.ORICHALCUM, 1.5f, -3.0f, properties));

    public static final DeferredItem<Item> ORICHALCUM_AXE = ITEMS.registerItem("orichalcum_axe",
            properties -> new Item(properties.axe(ModToolTiers.ORICHALCUM, 5, -3.2f)));

    public static final DeferredItem<Item> ORICHALCUM_HOE = ITEMS.registerItem("orichalcum_hoe",
            properties -> new HoeItem(ModToolTiers.ORICHALCUM, 0, -3.0f, properties));


    public static final DeferredItem<Item> STYGIAN_SWORD = ITEMS.registerItem("stygian_sword",
            properties -> new Item(properties.sword(ModToolTiers.STYGIAN, 3, -2.4f)));

    public static final DeferredItem<Item> STYGIAN_PICKAXE = ITEMS.registerItem("stygian_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolTiers.STYGIAN, 1, -2.8f)));

    public static final DeferredItem<Item> STYGIAN_SHOVEL = ITEMS.registerItem("stygian_shovel",
            properties -> new ShovelItem(ModToolTiers.STYGIAN, 1.5f, -3.0f, properties));

    public static final DeferredItem<Item> STYGIAN_AXE = ITEMS.registerItem("stygian_axe",
            properties -> new Item(properties.axe(ModToolTiers.STYGIAN, 5, -3.2f)));

    public static final DeferredItem<Item> LABYRINTH_BATTLE_AXE = ITEMS.registerItem("labyrinth_battle_axe",
            properties -> new Item(properties.axe(ModToolTiers.STYGIAN, 7, -3.2f)));

    public static final DeferredItem<Item> STYGIAN_HOE = ITEMS.registerItem("stygian_hoe",
            properties -> new HoeItem(ModToolTiers.STYGIAN, 0, -3.0f, properties));



    public static final DeferredItem<Item> ZENITH_SWORD = ITEMS.registerItem("zenith_sword",
            properties -> new Item(properties.sword(ModToolTiers.ZENITH, 3, -2.4f)));

    public static final DeferredItem<Item> ZENITH_PICKAXE = ITEMS.registerItem("zenith_pickaxe",
            properties -> new Item(properties.pickaxe(ModToolTiers.ZENITH, 1, -2.8f)));

    public static final DeferredItem<Item> ZENITH_SHOVEL = ITEMS.registerItem("zenith_shovel",
            properties -> new ShovelItem(ModToolTiers.ZENITH, 1.5f, -3.0f, properties));

    public static final DeferredItem<Item> ZENITH_AXE = ITEMS.registerItem("zenith_axe",
            properties -> new Item(properties.axe(ModToolTiers.ZENITH, 6, -3.2f)));

    public static final DeferredItem<Item> ZENITH_HOE = ITEMS.registerItem("zenith_hoe",
            properties -> new HoeItem(ModToolTiers.ZENITH, 0, -3.0f, properties));

    public static final DeferredItem<Item> ZENITH_BOW = ITEMS.registerItem("zenith_bow",
            properties -> new BowItem(properties
                    .durability(2500)       // Custom durability (Vanilla bow is 384)
                    .rarity(Rarity.EPIC)    // Epic rarity (gives purple text name)
                    .fireResistant()));   // Prevents destruction in lava/fire));

    public static final DeferredItem<Item> ZENITH_HELMET = ITEMS.registerItem("zenith_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ZENITH_ARMOR_MATERIAL, ArmorType.HELMET)));
    public static final DeferredItem<Item> ZENITH_CHESTPLATE = ITEMS.registerItem("zenith_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ZENITH_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> ZENITH_LEGGINGS = ITEMS.registerItem("zenith_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ZENITH_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> ZENITH_BOOTS = ITEMS.registerItem("zenith_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.ZENITH_ARMOR_MATERIAL, ArmorType.BOOTS)));

    //achievements
    public static final DeferredItem<Item> BEYOND_OMEGA_TAB_ICON = ITEMS.registerSimpleItem("beyond_omega_tab_icon");
    public static final DeferredItem<Item> MINOTAUR_ACHIEVEMENT_ICON = ITEMS.registerSimpleItem("minotaur_achievement_icon");



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
