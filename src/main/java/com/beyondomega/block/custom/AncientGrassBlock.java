package com.beyondomega.block.custom;

import com.beyondomega.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.SpreadingSnowyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AncientGrassBlock extends SpreadingSnowyBlock {

    public static final MapCodec<AncientGrassBlock> CODEC =
            simpleCodec(AncientGrassBlock::new);

    public AncientGrassBlock(BlockBehaviour.Properties properties) {
        super(properties, ModBlocks.ANCIENT_DIRT.getKey());
    }

    @Override
    protected MapCodec<? extends SpreadingSnowyBlock> codec() {
        return CODEC;
    }
}