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
        itemModels.generateFlatItem(ModItems.RAW_GREEK_STEEL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.GREEK_STEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.STYGIAN_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_STYGIAN.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_ORICHALCUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ORICHALCUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SUNSTONE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.AETHER_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MINOTAUR_HORN.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.ZENITH_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZENITH_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZENITH_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZENITH_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZENITH_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateBow(ModItems.ZENITH_BOW.get());

        itemModels.generateFlatItem(ModItems.GREEK_STEEL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.GREEK_STEEL_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.GREEK_STEEL_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.GREEK_STEEL_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.GREEK_STEEL_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.AETHER_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.AETHER_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.AETHER_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.AETHER_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.AETHER_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.ORICHALCUM_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ORICHALCUM_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ORICHALCUM_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ORICHALCUM_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ORICHALCUM_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.STYGIAN_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STYGIAN_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STYGIAN_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STYGIAN_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STYGIAN_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.LABYRINTH_BATTLE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);


        itemModels.generateTrimmableItem(ModItems.ZENITH_HELMET.get(), ModArmorMaterials.ZENITH_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.ZENITH_CHESTPLATE.get(), ModArmorMaterials.ZENITH_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ModItems.ZENITH_LEGGINGS.get(), ModArmorMaterials.ZENITH_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ModItems.ZENITH_BOOTS.get(), ModArmorMaterials.ZENITH_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        //achievement
        itemModels.generateFlatItem(ModItems.BEYOND_OMEGA_TAB_ICON.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MINOTAUR_ACHIEVEMENT_ICON.get(), ModelTemplates.FLAT_ITEM);

        /* BLOCKS */
        blockModels.createTrivialCube(ModBlocks.ZENITH_END_ORE.get());
        blockModels.createTrivialCube(ModBlocks.GREEK_STEEL_ORE.get());
        blockModels.createTrivialCube(ModBlocks.AETHER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ORICHALCUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.SUNSTONE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.STYGIAN_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ZENITH_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.KEYSTONE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ANCIENT_DIRT.get());
        blockModels.createTrivialCube(ModBlocks.SUNBAKED_DIRT.get());
        blockModels.createTrivialCube(ModBlocks.LIMESTONE.get());
        blockModels.createTrivialCube(ModBlocks.WEATHERED_LIMESTONE.get());
        blockModels.createTrivialCube(ModBlocks.MARBLE.get());
        blockModels.createTrivialCube(ModBlocks.MARBLE_BRICKS.get());

        blockModels.createAirLikeBlock(
                ModBlocks.MINOTAUR_ARENA_TRIGGER.get(),
                TextureMapping.getBlockTexture(ModBlocks.LIMESTONE.get())
        );

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

        TexturedModel.Provider SunbakedGrassBlockModel = TexturedModel.createDefault(
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
                ModBlocks.SUNBAKED_GRASS_BLOCK.get(),
                SunbakedGrassBlockModel
        );
    }
}