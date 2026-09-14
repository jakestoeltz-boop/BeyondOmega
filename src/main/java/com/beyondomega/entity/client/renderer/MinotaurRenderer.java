package com.beyondomega.entity.client.renderer;

import com.beyondomega.entity.client.model.MinotaurModel;
import com.beyondomega.entity.custom.MinotaurEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class MinotaurRenderer extends LivingEntityRenderer<
        MinotaurEntity,
        MinotaurRenderState,
        MinotaurModel> {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(
                    "beyondomega",
                    "textures/entity/minotaur.png"
            );

    public MinotaurRenderer(EntityRendererProvider.Context context) {
        super(
                context,
                new MinotaurModel(
                        context.bakeLayer(
                                MinotaurModel.LAYER_LOCATION
                        )
                ),
                1.5F
        );
    }

    @Override
    public MinotaurRenderState createRenderState() {
        return new MinotaurRenderState();
    }
    @Override
    public void extractRenderState(
            MinotaurEntity entity,
            MinotaurRenderState state,
            float partialTick
    ) {
        super.extractRenderState(entity, state, partialTick);

        state.animationTime = entity.tickCount + partialTick;

        state.isMoving = entity.getDeltaMovement().horizontalDistanceSqr() > 0.0001D;
        state.isSlamming = entity.isSlamming();
        state.slamAnimationTick = entity.getSlamAnimationTick();
    }
    @Override
    public Identifier getTextureLocation(MinotaurRenderState state) {
        return TEXTURE;
    }
}