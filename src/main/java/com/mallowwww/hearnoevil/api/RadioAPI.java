package com.mallowwww.hearnoevil.api;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.*;
@EventBusSubscriber
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
    @SubscribeEvent
    public static void tick(ServerTickEvent.Post event) {
//        var tick = new AudioTick(new short[48000 / 20]);
        HashMap<Frequency, AudioTick> audio = new HashMap<>();
        for (var x : INSTANCE.PUBLISHERS.entrySet()) {
            AudioTick tick_data = audio.computeIfAbsent(x.getKey(), (f) -> new AudioTick(new short[48000 / 20]));
            for (var y : x.getValue())
                if (y.get() instanceof AudioTransmittableData data)
                    tick_data = tick_data.add(data.get());
            audio.put(x.getKey(), tick_data);
        }
        for (var x : INSTANCE.SUBSCRIBERS.entrySet()) {
            for (var y : x.getValue())
                if (y instanceof AbstractAudioSubscriber subscriber)
                    subscriber.accept(new AudioTransmittableData(audio.getOrDefault(x.getKey(), new AudioTick(new short[48000 / 20]))));
        }

    }
}
