package com.beyondomega.entity;

import com.beyondomega.BeyondOmega;
import com.beyondomega.entity.custom.MinotaurEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, BeyondOmega.MOD_ID);

    public static final Supplier<EntityType<MinotaurEntity>> MINOTAUR =
            ENTITY_TYPES.register("minotaur", () -> EntityType.Builder.of(MinotaurEntity::new, MobCategory.MONSTER)
                    .sized(1.0f, 2.0f) // The physical hitbox size (width, height)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("beyondomega", "minotaur"))));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
