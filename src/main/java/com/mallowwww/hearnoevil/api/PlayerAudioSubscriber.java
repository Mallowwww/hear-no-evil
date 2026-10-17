package com.mallowwww.hearnoevil.api;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.audiochannel.AudioChannel;
import de.maxhenkel.voicechat.api.audiochannel.AudioPlayer;
import de.maxhenkel.voicechat.api.audiochannel.StaticAudioChannel;
import de.maxhenkel.voicechat.plugins.impl.audiochannel.StaticAudioChannelImpl;
import de.maxhenkel.voicechat.voice.server.Server;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class PlayerAudioSubscriber extends AbstractAudioSubscriber {
    private final ServerPlayer player;
    private final StaticAudioChannel channel;
    private final VoicechatServerApi api;

    private int counter = 0;

    public PlayerAudioSubscriber(ServerPlayer player, StaticAudioChannel channel, VoicechatServerApi api) {
        this.player = player;
        this.channel = channel;
        this.api = api;
    }

    @Override
    public void accept(AudioTransmittableData audioTransmittableData) {
        AudioPlayer audioPlayer = api.createAudioPlayer(
                channel,
                api.createEncoder(),
                audioTransmittableData.get().samples()
        );
        audioPlayer.startPlaying();

        if (counter++ % 20 == 0) {
//            var samples = audioTransmittableData.get().samples();
//            for (int i = 0; i < samples.length; i++) {
//                if (samples[i] > 0)
//                    System.out.println(i);
//            }
        }
    }

    @Override
    public Vec3 location() {
        return player.position();
    }

    @Override
    public ResourceKey<Level> level() {
        return player.level().dimension();
    }

    @Override
    public UUID id() {
        return player.getUUID();
    }
}
