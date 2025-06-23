//Code in Show Spawn Time by Seosean

package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.modules.PlayerVisibility;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ModelBiped.class)
public abstract class MixinModelBiped extends MixinModelBase {
    @Shadow
    public ModelRenderer bipedHead;
    @Shadow
    public ModelRenderer bipedHeadwear;
    @Shadow
    public ModelRenderer bipedBody;
    @Shadow
    public ModelRenderer bipedRightArm;
    @Shadow
    public ModelRenderer bipedLeftArm;
    @Shadow
    public ModelRenderer bipedRightLeg;
    @Shadow
    public ModelRenderer bipedLeftLeg;

    @Override
    @Shadow
    public abstract void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn);

    @Override
    public void render(Entity entityIn, float f, float g, float h, float i, float j, float scale) {
        setRotationAngles(f, g, h, i, j, scale, entityIn);
        GlStateManager.pushMatrix();
        if (PlayerVisibility.Companion.isSemiPV(entityIn)) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, PlayerVisibility.Companion.getAlpha(entityIn));
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.alphaFunc(516, 0.003921569F);
        }

        if (this.isChild) {
            GlStateManager.scale(0.75F, 0.75F, 0.75F);
            GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
            bipedHead.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
        } else {
            if (entityIn.isSneaking()) GlStateManager.translate(0.0F, 0.2F, 0.0F);
            bipedHead.render(scale);
        }
        bipedBody.render(scale);
        bipedRightArm.render(scale);
        bipedLeftArm.render(scale);
        bipedRightLeg.render(scale);
        bipedLeftLeg.render(scale);
        bipedHeadwear.render(scale);


        if (PlayerVisibility.Companion.isSemiPV(entityIn)) {
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.depthMask(true);
        }

        GlStateManager.popMatrix();
    }
}