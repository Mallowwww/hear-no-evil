package com.mallowwww.hearnoevil.api;

public interface TransmittableData<T> {
    T[] get();
    int size();
    int mediaType();
}
