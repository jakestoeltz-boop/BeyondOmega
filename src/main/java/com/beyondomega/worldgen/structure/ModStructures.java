package com.beyondomega.worldgen.structure;

import com.beyondomega.BeyondOmega;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructures {

    public static final DeferredRegister<StructureType<?>>
            STRUCTURE_TYPES =
            DeferredRegister.create(
                    Registries.STRUCTURE_TYPE,
                    BeyondOmega.MOD_ID
            );


    public static final DeferredHolder<
            StructureType<?>,
            StructureType<MinotaurLabyrinthStructure>
            > MINOTAUR_LABYRINTH =

            STRUCTURE_TYPES.register(
                    "minotaur_labyrinth",

                    () -> createStructureType(
                            MinotaurLabyrinthStructure.CODEC
                    )
            );


    private static <S extends Structure>
    StructureType<S> createStructureType(
            MapCodec<S> codec
    ) {

        return () -> codec;
    }


    public static void register(
            IEventBus eventBus
    ) {

        STRUCTURE_TYPES.register(eventBus);
    }
}
