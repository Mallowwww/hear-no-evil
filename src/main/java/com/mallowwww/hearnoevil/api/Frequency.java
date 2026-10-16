package com.mallowwww.hearnoevil.api;

import java.util.Objects;

public record Frequency(char band, short freq) {
    @Override
    public int hashCode() {
        return Objects.hashCode(band) ^ Objects.hashCode(freq);
    }
}
