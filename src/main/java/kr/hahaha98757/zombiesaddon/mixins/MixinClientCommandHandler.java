package kr.hahaha98757.zombiesaddon.mixins;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientCommandHandler.class)
public class MixinClientCommandHandler {

    @Inject(method = "executeCommand", at = @At("HEAD"), cancellable = true)
    private void onExecuteCommand(ICommandSender sender, String message, CallbackInfoReturnable<Integer> cir) {
        if (!message.startsWith("/")) cir.setReturnValue(0);
    }
}