package com.kittykitcatcat.kittysmodulardoors.content;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class DoorCreationStationRenderer extends TileEntityRenderer<DoorCreationStationTileEntity>
{

    public DoorCreationStationRenderer(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(DoorCreationStationTileEntity blockEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int light, int overlay)
    {
        if (this.renderDispatcher.renderInfo != null && blockEntity.getPos().distanceSq(this.renderDispatcher.renderInfo.getProjectedView(), true) <= 256d)
        {

        }
    }
}