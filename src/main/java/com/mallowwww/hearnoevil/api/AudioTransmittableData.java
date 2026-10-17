package com.mallowwww.hearnoevil.api;

public class AudioTransmittableData implements TransmittableData<AudioTick> {
    private final AudioTick data;

    public AudioTransmittableData(AudioTick data) {
        this.data = data;
    }

    @Override
    public AudioTick get() {
        return data;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public int mediaType() {
        return 0; // Represents audio data
    }
}
