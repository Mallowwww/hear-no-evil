package com.mallowwww.hearnoevil.api;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class RadioAPI {
    public static final RadioAPI INSTANCE = new RadioAPI();

    private final HashMap<Frequency, ArrayList<FrequencySubscriber<?>>> SUBSCRIBERS = new HashMap<>();
    private final HashMap<Frequency, ArrayList<FrequencyPublisher<?>>> PUBLISHERS = new HashMap<>();

    public RadioAPI() {

    }

    public <T extends TransmittableData<?>> void subscribe(FrequencySubscriber<T> subscriber, Frequency frequency) {
        SUBSCRIBERS.computeIfAbsent(
                frequency,
                (f) -> new ArrayList<>()
        ).add(subscriber);
    }
    public <T extends TransmittableData<?>> void publish(FrequencyPublisher<T> publisher, Frequency frequency) {
        PUBLISHERS.computeIfAbsent(
                frequency,
                (f) -> new ArrayList<>()
        ).add(publisher);
    }
    public <T extends TransmittableData<?>> void unSubscribe(FrequencySubscriber<T> subscriber, Frequency frequency) {
        SUBSCRIBERS.computeIfAbsent(
                frequency,
                (f) -> new ArrayList<>()
        ).remove(subscriber);
    }
    public <T extends TransmittableData<?>> void unPublish(FrequencyPublisher<T> publisher, Frequency frequency) {
        PUBLISHERS.computeIfAbsent(
                frequency,
                (f) -> new ArrayList<>()
        ).remove(publisher);
    }
}
