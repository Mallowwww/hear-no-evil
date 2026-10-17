package com.mallowwww.hearnoevil.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class VirtualAudioPublisher extends FrequencyPublisher<AudioTransmittableData> {
    private final UUID id;
    private final ResourceKey<Level> level;
    private final ResourceLocation audioPath;
    private final Frequency frequency;

    public static final Codec<VirtualAudioPublisher> CODEC = RecordCodecBuilder.create(i -> i.group(
            Frequency.CODEC.fieldOf("frequency").forGetter((VirtualAudioPublisher publisher) -> publisher.frequency),
            ResourceLocation.CODEC.fieldOf("level").forGetter((VirtualAudioPublisher publisher) -> publisher.level.location()),
            ResourceLocation.CODEC.fieldOf("audio_path").forGetter((VirtualAudioPublisher publisher) -> publisher.audioPath)
    ).apply(i, (f, level, audioPath) -> new VirtualAudioPublisher(
        f, ResourceKey.create(Registries.DIMENSION, level), audioPath
    )));

    public VirtualAudioPublisher(Frequency frequency, ResourceKey<Level> level, ResourceLocation audioPath) {
        this.frequency = frequency;
        this.level = level;
        id = UUID.randomUUID();
        this.audioPath = audioPath;
    }
    public Frequency frequency() {
        return this.frequency;
    }

    @Override
    AudioTransmittableData get() {
        short[] data = new short[48000 / 20];
        for (int i = 0; i < data.length; i++) {
            data[i] = (short) (Math.sin(i / 48000f * 200f * Math.PI) * 32000);
        }
        return new AudioTransmittableData(new AudioTick(data));
    }

    @Override
    Vec3 location() {
        return Vec3.ZERO;
    }

    @Override
    ResourceKey<Level> level() {
        return level;
    }

    @Override
    UUID id() {
        return id;
    }
}
