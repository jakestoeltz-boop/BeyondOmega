package com.beyondomega.datagen;

import com.beyondomega.BeyondOmega;
import com.beyondomega.block.ModBlocks;
import com.beyondomega.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Beyond Omega Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ZENITH_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ZENITH_CORE.get())
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .group("zenith_core")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.ZENITH_CORE.get(), 9)
                .requires(ModBlocks.ZENITH_BLOCK)
                .unlockedBy(getHasName(ModBlocks.ZENITH_BLOCK.get()), has(ModBlocks.ZENITH_BLOCK))
                .group("zenith_core")
                .save(output);

        List<ItemLike> ZENITH_CORE_SMELTABLES = List.of(ModBlocks.ZENITH_END_ORE);

        oreSmelting(ZENITH_CORE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.ZENITH_CORE.get(), 0.25f, 200, "azurite");
        oreBlasting(ZENITH_CORE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.ZENITH_CORE.get(), 0.25f, 100, "azurite");


        shaped(RecipeCategory.COMBAT, ModItems.ZENITH_SWORD.get())
                .pattern("A")
                .pattern("A")
                .pattern("S")
                .define('A', ModItems.ZENITH_CORE.get())
                .define('S', Items.BLAZE_ROD)
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                .save(output);

        shaped(RecipeCategory.TOOLS, ModItems.ZENITH_PICKAXE.get())
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.ZENITH_CORE.get())
                .define('S', Items.BLAZE_ROD)
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .save(output);

        shaped(RecipeCategory.TOOLS, ModItems.ZENITH_SHOVEL.get())
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .define('A', ModItems.ZENITH_CORE.get())
                .define('S', Items.BLAZE_ROD)
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .save(output);

        shaped(RecipeCategory.TOOLS, ModItems.ZENITH_AXE.get())
                .pattern("AA")
                .pattern("AS")
                .pattern(" S")
                .define('A', ModItems.ZENITH_CORE.get())
                .define('S', Items.BLAZE_ROD)
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .save(output);

        shaped(RecipeCategory.TOOLS, ModItems.ZENITH_HOE.get())
                .pattern("AA")
                .pattern(" S")
                .pattern(" S")
                .define('A', ModItems.ZENITH_CORE.get())
                .define('S', Items.BLAZE_ROD)
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .save(output);

        shaped(RecipeCategory.COMBAT, ModItems.ZENITH_BOW.get())
                .pattern(" BS")
                .pattern("A S")
                .pattern(" BS")
                .define('A', ModItems.ZENITH_CORE.get())
                .define('B', Items.BLAZE_ROD)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(output);

        shaped(RecipeCategory.COMBAT, ModItems.ZENITH_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .define('A', ModItems.ZENITH_CORE.get())
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .save(output);

        shaped(RecipeCategory.COMBAT, ModItems.ZENITH_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ZENITH_CORE.get())
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .save(output);

        shaped(RecipeCategory.COMBAT, ModItems.ZENITH_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.ZENITH_CORE.get())
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .save(output);

        shaped(RecipeCategory.COMBAT, ModItems.ZENITH_BOOTS.get())
                .pattern("A A")
                .pattern("A A")
                .define('A', ModItems.ZENITH_CORE.get())
                .unlockedBy(getHasName(ModItems.ZENITH_CORE.get()), has(ModItems.ZENITH_CORE))
                .save(output);

    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables,
                                                                RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result,
                                                                float experience, int cookingTime, String group, String fromDesc) {
        for(ItemLike itemlike : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), craftingCategory, cookingCategory, result, experience, cookingTime, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(output, BeyondOmega.MOD_ID + ":" + getItemName(result) + fromDesc + "_" + getItemName(itemlike));
        }
    }
}
