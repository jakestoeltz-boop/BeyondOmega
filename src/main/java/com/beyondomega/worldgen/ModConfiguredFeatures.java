package com.beyondomega.worldgen;

import com.beyondomega.BeyondOmega;
import com.beyondomega.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class ModConfiguredFeatures {
    // Feature --> Any Type of "build"
    // A Tree ==> Feature -> once Configured (you give values)
    // CF -> Describes HOW something looks like, how it is built.

    public static final ResourceKey<ConfiguredFeature<?, ?>> ZENITH_END_ORE_KEY = registerKey("zenith_end_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KEYSTONE_ORE_KEY = registerKey("keystone_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);
        RuleTest overworldReplaceables = new BlockMatchTest(Blocks.DEEPSLATE);


        register(context, ZENITH_END_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                ModBlocks.ZENITH_END_ORE.get().defaultBlockState(), 4));

        register(context, KEYSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldReplaceables,
                ModBlocks.KEYSTONE_ORE.get().defaultBlockState(), 6));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(BeyondOmega.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
