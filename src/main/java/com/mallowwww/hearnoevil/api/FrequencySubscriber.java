package com.mallowwww.hearnoevil.api;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public abstract class FrequencySubscriber<T extends TransmittableData<?>> {
    abstract void accept(T t);
    abstract Vec3 location();
    abstract ResourceKey<Level> level();
    abstract UUID id();
    @Override
    public int hashCode() {
        return id().hashCode();
    }
}
