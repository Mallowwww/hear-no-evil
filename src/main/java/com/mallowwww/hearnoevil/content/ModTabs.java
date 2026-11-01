package com.mallowwww.hearnoevil.content;

import com.mallowwww.hearnoevil.RadioMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = RadioMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModTabs
{
    public static DeferredRegister<CreativeModeTab> MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RadioMod.MOD_ID);

    @SubscribeEvent
    public static void buildTabs(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {
            event.accept(ModItems.AUDIO_COMPONENT);
            event.accept(ModItems.LOGIC_COMPONENT);
            event.accept(ModItems.RADIO_COMPONENT);
            event.accept(ModItems.RECORDING_COMPONENT);
            event.accept(ModItems.VIDEO_COMPONENT);
            event.accept(ModItems.SCULK_TENDRILS);
            event.accept(ModItems.REDSTONE_FIBERS);
        }
    }

    public static final Supplier<CreativeModeTab> MOD_TAB = MOD_TABS.register("mod_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + RadioMod.MOD_ID + ".tab"))
            .icon(() -> new ItemStack(ModItems.RADIO_COMPONENT.get()))
            .displayItems((p, o) ->
            {
                o.accept(ModItems.AUDIO_COMPONENT);
                o.accept(ModItems.LOGIC_COMPONENT);
                o.accept(ModItems.RADIO_COMPONENT);
                o.accept(ModItems.RECORDING_COMPONENT);
                o.accept(ModItems.VIDEO_COMPONENT);
                o.accept(ModItems.SCULK_TENDRILS);
                o.accept(ModItems.REDSTONE_FIBERS);
            })
            .build()
    );

    public static void register(IEventBus bus)
    {
        MOD_TABS.register(bus);
    }
}
