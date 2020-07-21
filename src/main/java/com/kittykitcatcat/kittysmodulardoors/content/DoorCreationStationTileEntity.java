package com.kittykitcatcat.kittysmodulardoors.content;

import com.kittykitcatcat.kittysmodulardoors.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

public class DoorCreationStationTileEntity extends TileEntity
{
    public DoorCreationStationTileEntity()
    {
        super(ModTileEntities.door_creation_station_tile_entity);
    }

    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);
        return compound;
    }

    @Override
    public void read(BlockState stateIn, CompoundNBT nbtIn)
    {
        super.read(stateIn, nbtIn);
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }


    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return new SUpdateTileEntityPacket(pos, 0, nbt);
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