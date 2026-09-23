package com.beyondomega.datagen;

import com.beyondomega.BeyondOmega;
import com.beyondomega.item.ModItems;
import com.beyondomega.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BeyondOmega.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.ZENITH_CORE.getKey());

        tag(ModTags.Items.ZENITH_REPAIRABLE)
                .add(ModItems.ZENITH_CORE.getKey());

        tag(ItemTags.SWORDS).add(ModItems.ZENITH_SWORD.getKey());
        tag(ItemTags.PICKAXES).add(ModItems.ZENITH_PICKAXE.getKey());
        tag(ItemTags.SHOVELS).add(ModItems.ZENITH_SHOVEL.getKey());
        tag(ItemTags.AXES).add(ModItems.ZENITH_AXE.getKey());
        tag(ItemTags.HOES).add(ModItems.ZENITH_HOE.getKey());
        tag(ItemTags.BOW_ENCHANTABLE).add(ModItems.ZENITH_BOW.getKey());

        tag(ItemTags.HEAD_ARMOR).add(ModItems.ZENITH_HELMET.getKey());
        tag(ItemTags.CHEST_ARMOR).add(ModItems.ZENITH_CHESTPLATE.getKey());
        tag(ItemTags.LEG_ARMOR).add(ModItems.ZENITH_LEGGINGS.getKey());
        tag(ItemTags.FOOT_ARMOR).add(ModItems.ZENITH_BOOTS.getKey());


        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.GREEK_STEEL_INGOT.getKey());

        tag(ModTags.Items.GREEK_STEEL_REPAIRABLE)
                .add(ModItems.GREEK_STEEL_INGOT.getKey());

        tag(ItemTags.SWORDS).add(ModItems.GREEK_STEEL_SWORD.getKey());
        tag(ItemTags.PICKAXES).add(ModItems.GREEK_STEEL_PICKAXE.getKey());
        tag(ItemTags.SHOVELS).add(ModItems.GREEK_STEEL_SHOVEL.getKey());
        tag(ItemTags.AXES).add(ModItems.GREEK_STEEL_AXE.getKey());
        tag(ItemTags.HOES).add(ModItems.GREEK_STEEL_HOE.getKey());

        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.ORICHALCUM_INGOT.getKey());

        tag(ModTags.Items.ORICHALCUM_REPAIRABLE)
                .add(ModItems.ORICHALCUM_INGOT.getKey());

        tag(ItemTags.SWORDS).add(ModItems.ORICHALCUM_SWORD.getKey());
        tag(ItemTags.PICKAXES).add(ModItems.ORICHALCUM_PICKAXE.getKey());
        tag(ItemTags.SHOVELS).add(ModItems.ORICHALCUM_SHOVEL.getKey());
        tag(ItemTags.AXES).add(ModItems.ORICHALCUM_AXE.getKey());
        tag(ItemTags.HOES).add(ModItems.ORICHALCUM_HOE.getKey());

        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.STYGIAN_INGOT.getKey());

        tag(ModTags.Items.STYGIAN_REPAIRABLE)
                .add(ModItems.STYGIAN_INGOT.getKey());

        tag(ItemTags.SWORDS).add(ModItems.STYGIAN_SWORD.getKey());
        tag(ItemTags.PICKAXES).add(ModItems.STYGIAN_PICKAXE.getKey());
        tag(ItemTags.SHOVELS).add(ModItems.STYGIAN_SHOVEL.getKey());
        tag(ItemTags.AXES).add(ModItems.STYGIAN_AXE.getKey());
        tag(ItemTags.HOES).add(ModItems.STYGIAN_HOE.getKey());

        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.AETHER_CRYSTAL.getKey());

        tag(ModTags.Items.AETHER_REPAIRABLE)
                .add(ModItems.AETHER_CRYSTAL.getKey());

        tag(ItemTags.SWORDS).add(ModItems.AETHER_SWORD.getKey());
        tag(ItemTags.PICKAXES).add(ModItems.AETHER_PICKAXE.getKey());
        tag(ItemTags.SHOVELS).add(ModItems.AETHER_SHOVEL.getKey());
        tag(ItemTags.AXES).add(ModItems.AETHER_AXE.getKey());
        tag(ItemTags.HOES).add(ModItems.AETHER_HOE.getKey());
    }
}
