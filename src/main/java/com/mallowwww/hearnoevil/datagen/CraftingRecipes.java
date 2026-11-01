package com.mallowwww.hearnoevil.datagen;

import com.mallowwww.hearnoevil.content.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class CraftingRecipes extends RecipeProvider
{
    public CraftingRecipes(PackOutput p_248933_, CompletableFuture<HolderLookup.Provider> p_323846_)
    {
        super(p_248933_, p_323846_);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output)
    {
        buildShaped(output);
    }

    public static void buildShaped(RecipeOutput output){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AUDIO_COMPONENT.get())
                .pattern("hs")
                .pattern("cr")
                .define('h', Items.GOAT_HORN)
                .define('s', ModItems.SCULK_TENDRILS)
                .define('c', Items.COPPER_INGOT)
                .define('r', Items.REDSTONE)
                .unlockedBy("has_tendrils", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCULK_TENDRILS.get()))
                .group("hne_components")
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LOGIC_COMPONENT.get())
                .pattern("fg")
                .pattern("cr")
                .define('f', Items.ROTTEN_FLESH)
                .define('g', Items.GHAST_TEAR)
                .define('c', Items.COPPER_INGOT)
                .define('r', Items.REDSTONE)
                .unlockedBy("has_tear", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GHAST_TEAR))
                .group("hne_components")
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RADIO_COMPONENT.get())
                .pattern("ba")
                .pattern("cr")
                .define('b', Items.BREEZE_ROD)
                .define('a', Items.AMETHYST_SHARD)
                .define('c', Items.COPPER_INGOT)
                .define('r', Items.REDSTONE)
                .unlockedBy("has_amethyst_shard", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AMETHYST_SHARD))
                .group("hne_components")
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RECORDING_COMPONENT.get())
                .pattern("ep")
                .pattern("cr")
                .define('e', Items.ECHO_SHARD)
                .define('p', Items.PHANTOM_MEMBRANE)
                .define('c', Items.COPPER_INGOT)
                .define('r', Items.REDSTONE)
                .unlockedBy("has_echo_shard", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ECHO_SHARD))
                .group("hne_components")
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VIDEO_COMPONENT.get())
                .pattern("eq")
                .pattern("cr")
                .define('e', Items.SPIDER_EYE)
                .define('q', Items.QUARTZ)
                .define('c', Items.COPPER_INGOT)
                .define('r', Items.REDSTONE)
                .unlockedBy("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(Items.QUARTZ))
                .group("hne_components")
                .save(output);
    }
}
