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

    public static final Codec<VirtualAudioPublisher> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.fieldOf("id").forGetter((VirtualAudioPublisher publisher) -> publisher.id.toString()),
            ResourceLocation.CODEC.fieldOf("level").forGetter((VirtualAudioPublisher publisher) -> publisher.level.location()),
            ResourceLocation.CODEC.fieldOf("audio_path").forGetter((VirtualAudioPublisher publisher) -> publisher.audioPath)
    ).apply(i, (id, level, audioPath) -> new VirtualAudioPublisher(
        ResourceKey.create(Registries.DIMENSION, level), audioPath, UUID.fromString(id)
    )));

    public VirtualAudioPublisher(ResourceKey<Level> level, ResourceLocation audioPath) {
        this.level = level;
        id = UUID.randomUUID();
        this.audioPath = audioPath;
    }
    private VirtualAudioPublisher(ResourceKey<Level> level, ResourceLocation audioPath, UUID id) {
        this.level = level;
        this.audioPath = audioPath;
        this.id = id;
    }

    @Override
    AudioTransmittableData get() {
        return null;
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
