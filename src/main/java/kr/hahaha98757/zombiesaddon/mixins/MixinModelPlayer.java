//Code in Show Spawn Time by Seosean

package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.modules.PlayerVisibility;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ModelPlayer.class)
public abstract class MixinModelPlayer extends MixinModelBiped {
    @Shadow
    public ModelRenderer bipedLeftLegwear;
    @Shadow
    public ModelRenderer bipedRightLegwear;
    @Shadow
    public ModelRenderer bipedLeftArmwear;
    @Shadow
    public ModelRenderer bipedRightArmwear;
    @Shadow
    public ModelRenderer bipedBodyWear;

    @Override
    public void render(Entity entityIn, float f, float g, float h, float i, float j, float scale) {
        super.render(entityIn, f, g, h, i, j, scale);
        GlStateManager.pushMatrix();
        if (PlayerVisibility.Companion.isSemiPV(entityIn)) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, PlayerVisibility.Companion.getAlpha(entityIn));
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.alphaFunc(516, 0.003921569F);
        }
        if (this.isChild) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
        } else {
            if (entityIn.isSneaking()) GlStateManager.translate(0.0F, 0.2F, 0.0F);
        }
        this.bipedLeftLegwear.render(scale);
        this.bipedRightLegwear.render(scale);
        this.bipedLeftArmwear.render(scale);
        this.bipedRightArmwear.render(scale);
        this.bipedBodyWear.render(scale);

        if (PlayerVisibility.Companion.isSemiPV(entityIn)) {
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.depthMask(true);
        }
        GlStateManager.popMatrix();
    }
}