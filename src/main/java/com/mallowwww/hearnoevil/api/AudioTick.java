package com.mallowwww.hearnoevil.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 * A server tick's worth of audio samples
 */
public record AudioTick(short[] samples) {
    public static final Codec<AudioTick> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.SHORT.listOf().fieldOf("samples").forGetter((AudioTick tick) -> {
                List<Short> list = new ArrayList<>();
                for (short x : tick.samples)
                    list.add(x);
                return list;
            })
    ).apply(i, samples -> {
        short[] newSamples = new short[samples.size()];
        for (int j = 0; j < samples.size(); j++) {
            newSamples[j] = samples.get(j);
        }
        return new AudioTick(newSamples);
    }));

    public AudioTick add(AudioTick toAdd) {
        short[] result = new short[samples.length];
        for (int i = 0; i < samples.length && i < toAdd.samples.length; i++) {
            result[i] = (short) (samples[i] + toAdd.samples[i]);
        }
        return new AudioTick(result);
    }
    public AudioTick subtract(AudioTick toAdd) {
        short[] result = new short[samples.length];
        for (int i = 0; i < samples.length && i < toAdd.samples.length; i++) {
            result[i] = (short) (samples[i] - toAdd.samples[i]);
        }
        return new AudioTick(result);
    }
    public AudioTick multiply(AudioTick toAdd) {
        short[] result = new short[samples.length];
        for (int i = 0; i < samples.length && i < toAdd.samples.length; i++) {
            result[i] = (short) (samples[i] * toAdd.samples[i]);
        }
        return new AudioTick(result);
    }
    public AudioTick divide(AudioTick toAdd) {
        short[] result = new short[samples.length];
        for (int i = 0; i < samples.length && i < toAdd.samples.length; i++) {
            result[i] = (short) (samples[i] / toAdd.samples[i]);
        }
        return new AudioTick(result);
    }

    public int size() {
        return samples.length;
    }
}
