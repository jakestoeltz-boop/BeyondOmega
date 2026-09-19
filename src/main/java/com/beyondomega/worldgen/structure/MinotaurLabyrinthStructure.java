package com.beyondomega.worldgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Optional;

public class MinotaurLabyrinthStructure extends Structure {

    /*
     * =========================================================
     * TERRAIN SETTINGS
     * =========================================================
     *
     * This does NOT change or carve terrain.
     *
     * It simply checks the terrain before allowing the
     * labyrinth to generate.
     */


    /*
     * Approximately half the width of the labyrinth.
     *
     * 28 means we check a 56-ish block wide area.
     *
     * If your labyrinth is wider than about 56 blocks,
     * increase this number.
     */
    private static final int TERRAIN_CHECK_RADIUS = 28;


    /*
     * How frequently terrain is sampled.
     *
     * 2 = check approximately every 2 blocks.
     *
     * Lower number = more accurate but more worldgen work.
     */
    private static final int TERRAIN_CHECK_STEP = 2;


    /*
     * Maximum height difference allowed across the entire
     * labyrinth footprint.
     *
     * 1 means the terrain can only change by ONE block.
     *
     * This is basically flat while still allowing the
     * structure to actually find reasonable spawn locations.
     *
     * Set this to 0 if you literally want perfectly level land.
     */
    private static final int MAX_HEIGHT_DIFFERENCE = 10;



    // =========================================================
    // CODEC
    // =========================================================

    public static final MapCodec<MinotaurLabyrinthStructure> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(

                            MinotaurLabyrinthStructure.settingsCodec(instance),

                            StructureTemplatePool.CODEC
                                    .fieldOf("start_pool")
                                    .forGetter(structure ->
                                            structure.startPool),

                            Identifier.CODEC
                                    .optionalFieldOf("start_jigsaw_name")
                                    .forGetter(structure ->
                                            structure.startJigsawName),

                            Codec.intRange(0, 30)
                                    .fieldOf("size")
                                    .forGetter(structure ->
                                            structure.size),

                            HeightProvider.CODEC
                                    .fieldOf("start_height")
                                    .forGetter(structure ->
                                            structure.startHeight),

                            Heightmap.Types.CODEC
                                    .optionalFieldOf(
                                            "project_start_to_heightmap"
                                    )
                                    .forGetter(structure ->
                                            structure.projectStartToHeightmap),

                            JigsawStructure.MaxDistance.CODEC
                                    .fieldOf(
                                            "max_distance_from_center"
                                    )
                                    .forGetter(structure ->
                                            structure.maxDistanceFromCenter),

                            DimensionPadding.CODEC
                                    .optionalFieldOf(
                                            "dimension_padding",
                                            JigsawStructure.DEFAULT_DIMENSION_PADDING
                                    )
                                    .forGetter(structure ->
                                            structure.dimensionPadding),

                            LiquidSettings.CODEC
                                    .optionalFieldOf(
                                            "liquid_settings",
                                            JigsawStructure.DEFAULT_LIQUID_SETTINGS
                                    )
                                    .forGetter(structure ->
                                            structure.liquidSettings)

                    ).apply(
                            instance,
                            MinotaurLabyrinthStructure::new
                    )
            );



    // =========================================================
    // STRUCTURE SETTINGS
    // =========================================================

    private final Holder<StructureTemplatePool> startPool;

    private final Optional<Identifier> startJigsawName;

    private final int size;

    private final HeightProvider startHeight;

    private final Optional<Heightmap.Types>
            projectStartToHeightmap;

    private final JigsawStructure.MaxDistance
            maxDistanceFromCenter;

    private final DimensionPadding dimensionPadding;

    private final LiquidSettings liquidSettings;



    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MinotaurLabyrinthStructure(
            StructureSettings settings,
            Holder<StructureTemplatePool> startPool,
            Optional<Identifier> startJigsawName,
            int size,
            HeightProvider startHeight,
            Optional<Heightmap.Types> projectStartToHeightmap,
            JigsawStructure.MaxDistance maxDistanceFromCenter,
            DimensionPadding dimensionPadding,
            LiquidSettings liquidSettings
    ) {

        super(settings);

        this.startPool =
                startPool;

        this.startJigsawName =
                startJigsawName;

        this.size =
                size;

        this.startHeight =
                startHeight;

        this.projectStartToHeightmap =
                projectStartToHeightmap;

        this.maxDistanceFromCenter =
                maxDistanceFromCenter;

        this.dimensionPadding =
                dimensionPadding;

        this.liquidSettings =
                liquidSettings;
    }



    // =========================================================
    // TERRAIN CHECK
    // =========================================================

    private static boolean isSuitableTerrain(
            GenerationContext context
    ) {

        ChunkPos chunkPos =
                context.chunkPos();


        /*
         * Jigsaw structure starts are based around the
         * candidate chunk.
         */
        int centerX =
                chunkPos.getMiddleBlockX();

        int centerZ =
                chunkPos.getMiddleBlockZ();


        int minimumHeight =
                Integer.MAX_VALUE;

        int maximumHeight =
                Integer.MIN_VALUE;



        /*
         * Check the terrain underneath approximately
         * the entire labyrinth footprint.
         */
        for (
                int x = centerX - TERRAIN_CHECK_RADIUS;
                x <= centerX + TERRAIN_CHECK_RADIUS;
                x += TERRAIN_CHECK_STEP
        ) {

            for (
                    int z = centerZ - TERRAIN_CHECK_RADIUS;
                    z <= centerZ + TERRAIN_CHECK_RADIUS;
                    z += TERRAIN_CHECK_STEP
            ) {


                /*
                 * WORLD_SURFACE_WG sees the top surface.
                 *
                 * In an ocean, this normally sees the top
                 * of the water.
                 */
                int worldSurfaceHeight =
                        context.chunkGenerator()
                                .getFirstOccupiedHeight(
                                        x,
                                        z,
                                        Heightmap.Types.WORLD_SURFACE_WG,
                                        context.heightAccessor(),
                                        context.randomState()
                                );


                /*
                 * OCEAN_FLOOR_WG ignores the water column
                 * and finds the actual solid terrain below.
                 */
                int oceanFloorHeight =
                        context.chunkGenerator()
                                .getFirstOccupiedHeight(
                                        x,
                                        z,
                                        Heightmap.Types.OCEAN_FLOOR_WG,
                                        context.heightAccessor(),
                                        context.randomState()
                                );


                // =============================================
                // WATER CHECK
                // =============================================

                /*
                 * If WORLD_SURFACE is above OCEAN_FLOOR,
                 * water exists at this location.
                 *
                 * Reject the entire structure location.
                 */
                if (worldSurfaceHeight
                        != oceanFloorHeight) {

                    return false;
                }


                // =============================================
                // FLATNESS CHECK
                // =============================================

                minimumHeight =
                        Math.min(
                                minimumHeight,
                                worldSurfaceHeight
                        );


                maximumHeight =
                        Math.max(
                                maximumHeight,
                                worldSurfaceHeight
                        );


                /*
                 * As soon as the terrain becomes too uneven,
                 * stop checking and reject this location.
                 */
                if (maximumHeight - minimumHeight
                        > MAX_HEIGHT_DIFFERENCE) {

                    return false;
                }
            }
        }


        /*
         * No water found.
         *
         * Terrain stayed within the allowed height
         * difference.
         */
        return true;
    }



    // =========================================================
    // STRUCTURE GENERATION
    // =========================================================

    @Override
    public Optional<GenerationStub> findGenerationPoint(
            GenerationContext context
    ) {

        /*
         * FIRST:
         *
         * Is this location actually suitable?
         */
        if (!isSuitableTerrain(context)) {

            /*
             * Nope.
             *
             * Minecraft will skip this candidate and the
             * labyrinth will NOT generate here.
             */
            return Optional.empty();
        }


        /*
         * Get the vertical offset from the JSON.
         *
         * In your case this is currently -20.
         */
        int startY =
                this.startHeight.sample(
                        context.random(),
                        new WorldGenerationContext(
                                context.chunkGenerator(),
                                context.heightAccessor()
                        )
                );


        ChunkPos chunkPos =
                context.chunkPos();


        /*
         * Starting point for the jigsaw structure.
         */
        BlockPos startPos =
                new BlockPos(
                        chunkPos.getMinBlockX(),
                        startY,
                        chunkPos.getMinBlockZ()
                );


        /*
         * Generate the same exact normal jigsaw structure
         * you already had.
         *
         * The only difference is that our custom terrain
         * check happened FIRST.
         */
        return JigsawPlacement.addPieces(
                context,
                this.startPool,
                this.startJigsawName,
                this.size,
                startPos,

                // useExpansionHack
                false,

                this.projectStartToHeightmap,

                this.maxDistanceFromCenter,

                PoolAliasLookup.EMPTY,

                this.dimensionPadding,

                this.liquidSettings
        );
    }



    // =========================================================
    // STRUCTURE TYPE
    // =========================================================

    @Override
    public StructureType<?> type() {

        return ModStructures.MINOTAUR_LABYRINTH.get();
    }
}
