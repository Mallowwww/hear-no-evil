package com.mallowwww.hearnoevil.content;

import com.mallowwww.hearnoevil.RadioMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems
{
    public static DeferredRegister<Item> MOD_ITEMS = DeferredRegister.createItems(RadioMod.MOD_ID);

    public static DeferredItem<Item> AUDIO_COMPONENT = (DeferredItem<Item>) MOD_ITEMS.register("audio_component", () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> LOGIC_COMPONENT = (DeferredItem<Item>) MOD_ITEMS.register("logic_component", () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> RADIO_COMPONENT = (DeferredItem<Item>) MOD_ITEMS.register("radio_component", () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> RECORDING_COMPONENT = (DeferredItem<Item>) MOD_ITEMS.register("recording_component", () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> VIDEO_COMPONENT = (DeferredItem<Item>) MOD_ITEMS.register("video_component", () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> SCULK_TENDRILS = (DeferredItem<Item>) MOD_ITEMS.register("sculk_tendrils", () -> new Item(new Item.Properties()));
    public static DeferredItem<Item> REDSTONE_FIBERS = (DeferredItem<Item>) MOD_ITEMS.register("redstone_fibers", () -> new Item(new Item.Properties()));

    public static void register(IEventBus bus)
    {
        MOD_ITEMS.register(bus);
    }
}
