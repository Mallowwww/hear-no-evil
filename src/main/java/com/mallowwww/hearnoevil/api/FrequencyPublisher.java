package com.mallowwww.hearnoevil.api;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public abstract class FrequencyPublisher<T extends TransmittableData<?>> {
    abstract T get();
    abstract Vec3 location();
    abstract ResourceKey<Level> level();
    abstract UUID id();
    @Override
    public int hashCode() {
        return id().hashCode();
    }
}
