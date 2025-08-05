package kr.hahaha98757.zombiesaddon.mixins;

import com.github.stachelbeere1248.zombiesutils.commands.SlaCommand;
import net.minecraft.command.ICommandSender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SlaCommand.class, remap = false)
public class MixinSlaCommand {
    @Inject(method = "func_71517_b", at = @At("HEAD"), cancellable = true)
    private void func_71517_b(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("sla_zombiesutils");
    }

    @Inject(method = "func_71518_a", at = @At("HEAD"), cancellable = true)
    private void func_71518_a(ICommandSender sender, CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("/sla_zombiesutils off\n/sla_zombiesutils offset [x] [x] [x]\n/sla_zombiesutils rotate\n/sla_zombiesutils mirror\n/sla_zombiesutils map <de|bb|aa|p>\n/sla_zombiesutils quick");
    }
}