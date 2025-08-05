package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.utils.Tools;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ConfigElement.class, remap = false)
public class MixinConfigElement {
    @Shadow
    private boolean isProperty;
    @Shadow
    private Property prop;
    @Shadow
    private ConfigCategory ctgy;

    @Inject(method = "getComment", at = @At("HEAD"), cancellable = true)
    private void getComment(CallbackInfoReturnable<String> cir) {
        if (!isProperty) cir.setReturnValue(ctgy.getComment());
        cir.setReturnValue(prop.comment.startsWith("zombiesaddon") ? Tools.getTranslatedString(prop.comment) : prop.comment);
    }
}