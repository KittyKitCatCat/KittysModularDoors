package com.kittykitcatcat.kittysmodulardoors.content;

import com.kittykitcatcat.kittysmodulardoors.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.io.DataOutput;

public class BoundingTileEntity extends TileEntity
{
    public BoundingTileEntity()
    {
        super(ModTileEntities.bounding_tile_entity);
    }

    BlockPos logicBlockPos;
    public CompoundNBT write(CompoundNBT compound)
    {
        if (logicBlockPos != null)
        {
            compound.putInt("logicBlockPosX", logicBlockPos.getX());
            compound.putInt("logicBlockPosY", logicBlockPos.getY());
            compound.putInt("logicBlockPosZ", logicBlockPos.getZ());
        }
        return compound;
    }

    @Override
    public void read(BlockState stateIn, CompoundNBT nbtIn)
    {
        logicBlockPos = new BlockPos(nbtIn.getInt("logicBlockPosX"),nbtIn.getInt("logicBlockPosY"),nbtIn.getInt("logicBlockPosZ"));
        super.read(stateIn, nbtIn);
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }



    public void handleUpdateTag(CompoundNBT tag)
    {
        read(getBlockState(), tag);
    }
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        handleUpdateTag(pkt.getNbtCompound());
    }
}