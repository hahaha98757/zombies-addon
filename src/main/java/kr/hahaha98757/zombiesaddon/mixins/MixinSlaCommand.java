package kr.hahaha98757.zombiesaddon.mixins;

import com.github.stachelbeere1248.zombiesutils.commands.SlaCommand;
import net.minecraft.command.ICommandSender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SlaCommand.class)
public class MixinSlaCommand {

    @Inject(method = "func_71517_b", at = @At("HEAD"), cancellable = true, remap = false)
    private void func_71517_b(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("sla_zombiesutils");
    }

    @Inject(method = "func_71518_a", at = @At("HEAD"), cancellable = true, remap = false)
    private void func_71518_a(ICommandSender sender, CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("/sla_zombiesutils off\n/sla offset [x] [x] [x]\n/sla rotate\n/sla mirror\n/sla map <de|bb|aa|p>\n/sla quick");
    }
}