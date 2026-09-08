package com.beyondomega.item;

import com.beyondomega.BeyondOmega;
import net.minecraft.world.item.Item;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BeyondOmega.MOD_ID);

    public static final DeferredItem<Item> RIFTSTONE_SHARD = ITEMS.registerSimpleItem("riftstone_shard");
    public static final DeferredItem<Item> UMBRAL_SHARD = ITEMS.registerSimpleItem("umbral_shard");
    public static final DeferredItem<Item> ZENITH_CORE = ITEMS.registerSimpleItem("zenith_core");

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
