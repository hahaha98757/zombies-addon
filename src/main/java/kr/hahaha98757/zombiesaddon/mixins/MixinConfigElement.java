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

@Mixin(ConfigElement.class)
public class MixinConfigElement {
    @Shadow(remap = false)
    private boolean isProperty;
    @Shadow(remap = false)
    private Property prop;
    @SuppressWarnings("SpellCheckingInspection")
    @Shadow(remap = false)
    private ConfigCategory ctgy;

    @Inject(method = "getComment", at = @At("HEAD"), cancellable = true, remap = false)
    private void getComment(CallbackInfoReturnable<String> cir) {
        if (!isProperty) cir.setReturnValue(ctgy.getComment());
        cir.setReturnValue(prop.comment.startsWith("zombiesaddon") ? Tools.getTranslatedString(prop.comment) : prop.comment);
    }
}