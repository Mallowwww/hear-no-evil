package com.mallowwww.hearnoevil.registry;

import com.mallowwww.hearnoevil.RadioMod;
import com.mallowwww.hearnoevil.api.VirtualAudioPublisher;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ResourcePackRegistries {
    public static final ResourceKey<Registry<VirtualAudioPublisher>> VIRTUAL_AUDIO_PUBLISHER_REGISTRY = ResourceKey.createRegistryKey(RadioMod.path("virtual_audio_publisher"));

    @SubscribeEvent
    public static void register(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(
                VIRTUAL_AUDIO_PUBLISHER_REGISTRY,
                VirtualAudioPublisher.CODEC
        );

    }
}
