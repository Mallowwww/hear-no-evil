package com.mallowwww.hearnoevil;

import com.mallowwww.hearnoevil.api.Frequency;
import com.mallowwww.hearnoevil.api.PlayerAudioSubscriber;
import com.mallowwww.hearnoevil.api.RadioAPI;
import de.maxhenkel.voicechat.api.*;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

@ForgeVoicechatPlugin
public class RadioVoicechatPlugin implements VoicechatPlugin {

    private static VoicechatApi API;
    public static String RADIO_VOLUME_CATEGORY = "radio";

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
        registration.registerEvent(VoicechatServerStartedEvent.class, this::onServerStarted);
    }

    private void onServerStarted(VoicechatServerStartedEvent event) {
        VoicechatServerApi api = event.getVoicechat();
        VolumeCategory musicDiscs = api.volumeCategoryBuilder()
                .setId(RADIO_VOLUME_CATEGORY)
                .setName("Radios")
                .setDescription("The volume of all voice-enabled radios")
                .build();

        api.registerVolumeCategory(musicDiscs);
    }

    public static void subscribePlayer(ServerPlayer player, Frequency frequency) {
        var api = (VoicechatServerApi) API;
        var channel = api.createStaticAudioChannel(UUID.randomUUID());
        if (channel == null) throw new RuntimeException("SMTH BAD HAPPENED");
        channel.setCategory(RADIO_VOLUME_CATEGORY);
        channel.addTarget(api.getConnectionOf(api.fromServerPlayer(player)));
        RadioAPI.INSTANCE.subscribe(new PlayerAudioSubscriber(
                player, channel, api
        ), frequency);
    }

}
