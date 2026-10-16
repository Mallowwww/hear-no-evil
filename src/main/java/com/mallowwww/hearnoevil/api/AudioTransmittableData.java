package com.mallowwww.hearnoevil.api;

public class AudioTransmittableData implements TransmittableData<Integer> {
    private final Integer[] data;

    public AudioTransmittableData(Integer[] data) {
        this.data = data;
    }

    @Override
    public Integer[] get() {
        return data;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public int mediaType() {
        return 0; // Represents audio data
    }
}
