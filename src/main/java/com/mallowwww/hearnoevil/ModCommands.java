package com.mallowwww.hearnoevil;

import com.mallowwww.hearnoevil.api.Frequency;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class ModCommands {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("radio")
                .then(Commands.literal("tune_in")
                        .then(Commands.argument("frequency", new Frequency.Argument())
                            .executes(context -> {
                                // TODO tune into a frequency
                                var player = context.getSource().getPlayer();
                                if (!(player instanceof ServerPlayer serverPlayer))
                                    return 1;
                                RadioVoicechatPlugin.subscribePlayer(serverPlayer, context.getArgument("frequency", Frequency.class));

                                return 1;
                            })
                        )
                )
        );
    }
}
