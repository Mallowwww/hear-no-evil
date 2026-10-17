package com.mallowwww.hearnoevil;

import com.mallowwww.hearnoevil.api.Frequency;
import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModArgumentTypes {
    public static final DeferredRegister<ArgumentTypeInfo<?, ?>> REGISTRY = DeferredRegister.create(Registries.COMMAND_ARGUMENT_TYPE, RadioMod.MOD_ID);
    public static final DeferredHolder<ArgumentTypeInfo<?, ?>, SingletonArgumentInfo<Frequency.Argument>> FREQUENCY = REGISTRY.register("frequency", location -> ArgumentTypeInfos.registerByClass(
            Frequency.Argument.class,
            SingletonArgumentInfo.contextFree(Frequency.Argument::new)
    ));
    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }

}
