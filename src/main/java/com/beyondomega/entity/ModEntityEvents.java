package com.beyondomega.entity;

import com.beyondomega.BeyondOmega;
import com.beyondomega.entity.custom.MinotaurEntity;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = BeyondOmega.MOD_ID)
public class ModEntityEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {

        event.put(
                ModEntities.MINOTAUR.get(),
                MinotaurEntity.createAttributes().build()
        );
    }
}

