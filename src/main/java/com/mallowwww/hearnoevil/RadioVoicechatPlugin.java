package com.mallowwww.hearnoevil;

import com.mallowwww.hearnoevil.api.Frequency;
import com.mallowwww.hearnoevil.api.PlayerAudioSubscriber;
import com.mallowwww.hearnoevil.api.RadioAPI;
import de.maxhenkel.voicechat.api.*;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

@ForgeVoicechatPlugin
public class RadioVoicechatPlugin implements VoicechatPlugin {

    private static VoicechatApi API;

    /**
     * @return the unique ID for this voice chat plugin
     */
    @Override
    public String getPluginId() {
        return RadioMod.MOD_ID;
    }

    /**
     * Called when the voice chat initializes the plugin.
     *
     * @param api the voice chat API
     */
    @Override
    public void initialize(VoicechatApi api) {
        RadioMod.LOGGER.info("Example voice chat plugin initialized!");
        API = api;

    }

    /**
     * Called once by the voice chat to register all events.
     *
     * @param registration the event registration
     */
    @Override
    public void registerEvents(EventRegistration registration) {
        // TODO register your events
    }

    public static void subscribePlayer(ServerPlayer player, Frequency frequency) {
        var api = (VoicechatServerApi) API;
        RadioAPI.INSTANCE.subscribe(new PlayerAudioSubscriber(
                player, api.createStaticAudioChannel(UUID.randomUUID()), api
        ), frequency);
    }

}
