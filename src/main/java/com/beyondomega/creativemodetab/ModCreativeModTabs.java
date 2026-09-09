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
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BeyondOmega.MOD_ID);

    public static final Supplier<CreativeModeTab> ZENITH_CORE_ITEMS_TAB = CREATIVE_MODE_TABS.register("zenith_core_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ZENITH_CORE.get()))
                    .title(Component.translatable("creativetab.beyondomega.zenith_core_items"))
                    .withTabsBefore(CreativeModeTabs.INGREDIENTS)
                    .withTabsAfter(Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, "zenith_end_ore_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RIFTSTONE_SHARD);
                        output.accept(ModItems.UMBRAL_SHARD);
                        output.accept(ModItems.ZENITH_CORE);

                        output.accept(ModItems.ZENITH_SWORD);
                        output.accept(ModItems.ZENITH_PICKAXE);
                        output.accept(ModItems.ZENITH_SHOVEL);
                        output.accept(ModItems.ZENITH_AXE);
                        output.accept(ModItems.ZENITH_HOE);
                        output.accept(ModItems.ZENITH_BOW);
                    }).build());

    public static final Supplier<CreativeModeTab> ZENITH_END_ORE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("zenith_end_ore_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ZENITH_END_ORE.get()))
                    .title(Component.translatable("creativetab.beyondomega.zenith_ore_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ZENITH_END_ORE);
                        output.accept(ModBlocks.ZENITH_BLOCK);
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
