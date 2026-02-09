package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.utils.MixinToolsKt;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ModelBiped.class, priority = 1100)
public class MixinModelBiped {
    @Inject(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelBiped;isChild:Z", opcode = Opcodes.GETFIELD))
    private void renderPre(Entity entityIn, float f, float g, float h, float i, float j, float scale, CallbackInfo ci) {
        MixinToolsKt.renderPre(entityIn);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;popMatrix()V", ordinal = 1))
    private void renderPost(Entity entityIn, float f, float g, float h, float i, float j, float scale, CallbackInfo ci) {
        MixinToolsKt.renderPost(entityIn);
    }
}