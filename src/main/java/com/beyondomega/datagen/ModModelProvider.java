package com.beyondomega.datagen;

import com.beyondomega.BeyondOmega;
import com.beyondomega.block.ModBlocks;
import com.beyondomega.block.custom.GreekPortalBlock;
import com.beyondomega.block.custom.PortalQuadrant;
import com.beyondomega.item.ModArmorMaterials;
import com.beyondomega.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, BeyondOmega.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.RIFTSTONE_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.UMBRAL_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ZENITH_CORE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.KEYSTONE_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.KEYSTONE_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.KEYSTONE_PORTAL_CORE.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.ZENITH_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZENITH_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZENITH_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZENITH_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZENITH_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateBow(ModItems.ZENITH_BOW.get());

        itemModels.generateTrimmableItem(ModItems.ZENITH_HELMET.get(), ModArmorMaterials.ZENITH_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.ZENITH_CHESTPLATE.get(), ModArmorMaterials.ZENITH_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ModItems.ZENITH_LEGGINGS.get(), ModArmorMaterials.ZENITH_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ModItems.ZENITH_BOOTS.get(), ModArmorMaterials.ZENITH_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        /* BLOCKS */
        blockModels.createTrivialCube(ModBlocks.ZENITH_END_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ZENITH_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.KEYSTONE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ANCIENT_DIRT.get());

        ModelTemplate greekPortalTemplate = new ModelTemplate(
                Optional.of(
                        Identifier.fromNamespaceAndPath(
                                BeyondOmega.MOD_ID,
                                "block/greek_portal_template"
                        )
                ),
                Optional.empty(),
                TextureSlot.TEXTURE
        );

        var portal = ModBlocks.GREEK_PORTAL.get();

        var nwModel = greekPortalTemplate.create(
                Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, "block/greek_portal_nw"),
                new TextureMapping()
                        .put(
                                TextureSlot.ALL,
                                TextureMapping.getBlockTexture(portal, "_nw")
                        ),
                blockModels.modelOutput
        );

        var neModel = greekPortalTemplate.create(
                Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, "block/greek_portal_ne"),
                new TextureMapping()
                        .put(
                                TextureSlot.ALL,
                                TextureMapping.getBlockTexture(portal, "_ne")
                        ),
                blockModels.modelOutput
        );

        var swModel = greekPortalTemplate.create(
                Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, "block/greek_portal_sw"),
                new TextureMapping()
                        .put(
                                TextureSlot.ALL,
                                TextureMapping.getBlockTexture(portal, "_sw")
                        ),
                blockModels.modelOutput
        );

        var seModel = greekPortalTemplate.create(
                Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, "block/greek_portal_se"),
                new TextureMapping()
                        .put(
                                TextureSlot.ALL,
                                TextureMapping.getBlockTexture(portal, "_se")
                        ),
                blockModels.modelOutput
        );
        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(portal)
                        .with(
                                PropertyDispatch.initial(GreekPortalBlock.QUADRANT)
                                        .select(
                                                PortalQuadrant.NW,
                                                BlockModelGenerators.plainVariant(nwModel)
                                        )
                                        .select(
                                                PortalQuadrant.NE,
                                                BlockModelGenerators.plainVariant(neModel)
                                        )
                                        .select(
                                                PortalQuadrant.SW,
                                                BlockModelGenerators.plainVariant(swModel)
                                        )
                                        .select(
                                                PortalQuadrant.SE,
                                                BlockModelGenerators.plainVariant(seModel)
                                        )
                        )
        );

        TexturedModel.Provider greekPortalFrameModel = TexturedModel.createDefault(
                block -> new TextureMapping()
                        .put(
                                TextureSlot.SIDE,
                                TextureMapping.getBlockTexture(block, "_side")
                        )
                        .put(
                                TextureSlot.TOP,
                                TextureMapping.getBlockTexture(block, "_top")
                        )
                        .put(
                                TextureSlot.BOTTOM,
                                TextureMapping.getBlockTexture(block, "_bottom")
                        )
                        .put(
                                TextureSlot.PARTICLE,
                                TextureMapping.getBlockTexture(block, "_side")
                        ),
                ModelTemplates.CUBE_BOTTOM_TOP
        );

        blockModels.createTrivialBlock(
                ModBlocks.GREEK_PORTAL_FRAME.get(),
                greekPortalFrameModel
        );

        TexturedModel.Provider AncientGrassBlockModel = TexturedModel.createDefault(
                block -> new TextureMapping()
                        .put(
                                TextureSlot.SIDE,
                                TextureMapping.getBlockTexture(block, "_side")
                        )
                        .put(
                                TextureSlot.TOP,
                                TextureMapping.getBlockTexture(block, "_top")
                        )
                        .put(
                                TextureSlot.BOTTOM,
                                TextureMapping.getBlockTexture(block, "_bottom")
                        )
                        .put(
                                TextureSlot.PARTICLE,
                                TextureMapping.getBlockTexture(block, "_side")
                        ),
                ModelTemplates.CUBE_BOTTOM_TOP
        );

        blockModels.createTrivialBlock(
                ModBlocks.ANCIENT_GRASS_BLOCK.get(),
                AncientGrassBlockModel
        );
    }
}
