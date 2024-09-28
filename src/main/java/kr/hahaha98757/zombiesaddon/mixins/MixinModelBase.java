//Code in Show Spawn Time by Seosean

package kr.hahaha98757.zombiesaddon.mixins;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ModelBase.class)
public abstract class MixinModelBase {
    @Shadow
    public boolean isChild = true;

    @Shadow
    public abstract void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale);

    @Shadow
    public abstract void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn);
}