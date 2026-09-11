package com.beyondomega.item;

import com.beyondomega.BeyondOmega;
import com.beyondomega.tags.ModTags;
import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public class ModArmorMaterials {
    public static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

    public static final ResourceKey<EquipmentAsset> ZENITH_KEY = ResourceKey.create(ROOT_ID, Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, "zenith"));

    public static final ArmorMaterial ZENITH_ARMOR_MATERIAL = new ArmorMaterial(1200,
            makeDefense(5, 7, 9, 5, 22), 17, SoundEvents.ARMOR_EQUIP_COPPER,
            4f, 0.3f, ModTags.Items.ZENITH_REPAIRABLE, ZENITH_KEY);


    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(
                Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body)
        );
    }
}
