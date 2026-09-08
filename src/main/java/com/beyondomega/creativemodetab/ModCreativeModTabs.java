package com.beyondomega.creativemodetab;

import com.beyondomega.BeyondOmega;
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
                    .withTabsAfter(Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, "zenith_ore_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RIFTSTONE_SHARD);
                        output.accept(ModItems.UMBRAL_SHARD);
                        output.accept(ModItems.ZENITH_CORE);
                    }).build());

    public static final Supplier<CreativeModeTab> ZENITH_ORE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("zenith_ore_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ZENITH_CORE.get()))
                    .title(Component.translatable("creativetab.beyondomega.zenith_ore_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RIFTSTONE_SHARD);
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
