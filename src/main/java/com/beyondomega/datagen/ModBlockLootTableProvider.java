package com.beyondomega.datagen;

import com.beyondomega.block.ModBlocks;
import com.beyondomega.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ZENITH_END_ORE.get());
        dropSelf(ModBlocks.ZENITH_BLOCK.get());
        dropSelf(ModBlocks.GREEK_PORTAL_FRAME.get());

        add(ModBlocks.GREEK_PORTAL.get(), noDrop());

        add(ModBlocks.ZENITH_END_ORE.get(),
                createMultipleOreDrops(ModBlocks.ZENITH_END_ORE.get(), ModItems.ZENITH_CORE.get(), 1, 2));

        add(
                ModBlocks.KEYSTONE_ORE.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(
                                                                ModItems.KEYSTONE_SHARD.get()
                                                        )
                                                        .setWeight(7)
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        UniformGenerator.between(1.0F, 3.0F)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(
                                                                ModItems.KEYSTONE_FRAGMENT.get()
                                                        )
                                                        .setWeight(3)
                                                        .apply(
                                                                SetItemCountFunction.setCount(
                                                                        ConstantValue.exactly(1)
                                                                )
                                                        )
                                        )
                        )
        );

    }

    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
