package com.kittykitcatcat.kittysmodulardoors.content;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class LogicBlockRenderer extends TileEntityRenderer<LogicTileEntity>
{
    public LogicBlockRenderer(TileEntityRendererDispatcher rendererDispatcherIn)
    {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(LogicTileEntity blockEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int light, int overlay)
    {
        matrixStack.push();
        if (blockEntity.boundingBlocks != null && !blockEntity.boundingBlocks.isEmpty())
        {
            for (BlockPos pos : blockEntity.boundingBlocks)
            {
                IVertexBuilder bufferBuilder = iRenderTypeBuffer.getBuffer(Atlases.getCutoutBlockType());
                World world = blockEntity.getWorld();
                putVertex(world, bufferBuilder, pos.getX(), pos.getY(), pos.getZ());
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY(), pos.getZ());
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY(), pos.getZ() + 1);
                putVertex(world, bufferBuilder, pos.getX(), pos.getY(), pos.getZ() + 1);

                // top
                putVertex(world, bufferBuilder, pos.getX(), pos.getY() + 1, pos.getZ());
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY() + 1, pos.getZ());
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
                putVertex(world, bufferBuilder, pos.getX(), pos.getY() + 1, pos.getZ() + 1);

                // left
                putVertex(world, bufferBuilder, pos.getX(), pos.getY(), pos.getZ());
                putVertex(world, bufferBuilder, pos.getX(), pos.getY() + 1, pos.getZ());
                putVertex(world, bufferBuilder, pos.getX(), pos.getY() + 1, pos.getZ() + 1);
                putVertex(world, bufferBuilder, pos.getX(), pos.getY(), pos.getZ() + 1);

                // right
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY(), pos.getZ());
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY() + 1, pos.getZ());
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY(), pos.getZ() + 1);

                // front
                putVertex(world, bufferBuilder, pos.getX(), pos.getY(), pos.getZ());
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY(), pos.getZ());
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY() + 1, pos.getZ());
                putVertex(world, bufferBuilder, pos.getX(), pos.getY() + 1, pos.getZ());

                // back
                putVertex(world, bufferBuilder, pos.getX(), pos.getY(), pos.getZ() + 1);
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY(), pos.getZ() + 1);
                putVertex(world, bufferBuilder, pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
                putVertex(world, bufferBuilder, pos.getX(), pos.getY() + 1, pos.getZ() + 1);
            }
        }
    }

    public static void putVertex(World world, IVertexBuilder bufferBuilder, float x, float y, float z)
    {
        bufferBuilder.pos(x,y,z).lightmap(getPackedLight(world,new BlockPos((int)x,(int)y,(int)z))).color(1,1,1,1).endVertex();
    }

    public static int getPackedLight(World world, BlockPos pos)
    {
        int blockLight = world.getLightFor(LightType.BLOCK, pos);
        int skyLight = world.getLightFor(LightType.SKY, pos);
        return LightTexture.packLight(blockLight, skyLight);
    }
}