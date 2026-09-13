package com.beyondomega.entity.client;

import com.beyondomega.BeyondOmega;
import com.beyondomega.entity.ModEntities;
import com.beyondomega.entity.client.model.MinotaurModel;
import com.beyondomega.entity.client.renderer.MinotaurRenderer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(
        modid = BeyondOmega.MOD_ID,
        value = Dist.CLIENT
)
public class ModEntityRenderers {

    @SubscribeEvent
    public static void registerLayerDefinitions(
            EntityRenderersEvent.RegisterLayerDefinitions event
    ) {

        event.registerLayerDefinition(
                MinotaurModel.LAYER_LOCATION,
                MinotaurModel::createBodyLayer
        );
    }

    @SubscribeEvent
    public static void registerEntityRenderers(
            EntityRenderersEvent.RegisterRenderers event
    ) {

        event.registerEntityRenderer(
                ModEntities.MINOTAUR.get(),
                MinotaurRenderer::new
        );
    }
}
