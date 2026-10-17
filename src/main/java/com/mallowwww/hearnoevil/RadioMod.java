package com.mallowwww.hearnoevil;

import com.mallowwww.hearnoevil.api.RadioAPI;
import com.mallowwww.hearnoevil.registry.ResourcePackRegistries;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(RadioMod.MOD_ID)
public class RadioMod {

    public static final String MOD_ID = "hearnoevil";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RadioMod(IEventBus modEventBus) {
        modEventBus.addListener(this::setup);

        ModArgumentTypes.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
    }

    private void setup(FMLCommonSetupEvent event) {

    }

    public static ResourceLocation path(String s) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, s);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        var registry = event.getServer().registryAccess().registry(ResourcePackRegistries.VIRTUAL_AUDIO_PUBLISHER_REGISTRY).get();

        registry.forEach(publisher -> {
            RadioAPI.INSTANCE.publish(
                publisher, publisher.frequency()
            );
            System.out.println(publisher.frequency().toString());
        });
    }

}
