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

    public static final DeferredItem<Item> ZENITH_SWORD = ITEMS.registerItem("zenith_sword",
            properties -> new Item(properties.sword(ModToolTiers.ZENITH, 15, -2.4f)));

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
