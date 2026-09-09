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
    }
}
