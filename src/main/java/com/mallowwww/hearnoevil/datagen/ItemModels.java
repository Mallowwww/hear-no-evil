package com.mallowwww.hearnoevil.datagen;

import com.mallowwww.hearnoevil.RadioMod;
import com.mallowwww.hearnoevil.content.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider
{
    public ItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RadioMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        basicItem(ModItems.AUDIO_COMPONENT.get());
        basicItem(ModItems.LOGIC_COMPONENT.get());
        basicItem(ModItems.RADIO_COMPONENT.get());
        basicItem(ModItems.RECORDING_COMPONENT.get());
        basicItem(ModItems.VIDEO_COMPONENT.get());
    }
}
