package com.kittykitcatcat.kittysmodulardoors.content;

import com.kittykitcatcat.kittysmodulardoors.init.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

import static com.kittykitcatcat.kittysmodulardoors.KittysModularDoorsMod.*;

public class LogicTileEntity extends TileEntity
{
    public LogicTileEntity()
    {
        super(ModTileEntities.logic_tile_entity);
    }

    public enum animationTypeEnum
    {
        slideUp(0),
        slideDirection(1),
        slideDown(2),
        openUp(4),
        openDirection(5),
        openDown(6);

        public final int type;

        animationTypeEnum(int type) { this.type = type;}
    }
    public List<BlockPos> boundingBlocks;
    public int width;
    public int height;
    int animationType;
    int itemID;
    public CompoundNBT write(CompoundNBT compound)
    {
        if (width != 0)
        {
            compound.putInt(widthNBT, width);
        }
        if (height != 0)
        {
            compound.putInt(heightNBT, height);
        }
        if (animationType != 0)
        {
            compound.putInt(animationNBT, animationType);
        }
        if (itemID != 0)
        {
            compound.putInt(itemNBT, itemID);
        }
        if (boundingBlocks != null && !boundingBlocks.isEmpty())
        {
            compound.putIntArray(logicBlockPositionNBT + "x", getBoundingBlockAxis(Direction.Axis.X));
            compound.putIntArray(logicBlockPositionNBT + "y", getBoundingBlockAxis(Direction.Axis.Y));
            compound.putIntArray(logicBlockPositionNBT + "z", getBoundingBlockAxis(Direction.Axis.Z));
        }
        return compound;
    }
    public List<Integer> getBoundingBlockAxis(Direction.Axis axis)
    {
        List<Integer> list = new ArrayList<>();
        for (BlockPos pos : boundingBlocks)
        {
            switch(axis)
            {
                case X:
                {
                    list.add(pos.getX());
                    break;
                }
                case Y:
                {
                    list.add(pos.getY());
                    break;
                }
                case Z:
                {
                    list.add(pos.getZ());
                    break;
                }
            }
        }
        return list;
    }
    @Override
    public void read(BlockState stateIn, CompoundNBT nbtIn)
    {
        width = nbtIn.getInt(widthNBT);
        height = nbtIn.getInt(heightNBT);
        animationType = nbtIn.getInt(animationNBT);
        itemID = nbtIn.getInt(itemNBT);
        List<BlockPos> newBoundingBlocks = new ArrayList();
        int[] xPositions = nbtIn.getIntArray(logicBlockPositionNBT + "x");
        int[] yPositions = nbtIn.getIntArray(logicBlockPositionNBT + "y");
        int[] zPositions = nbtIn.getIntArray(logicBlockPositionNBT + "z");
        for (int i = 0; i < xPositions.length; i++)
        {
            BlockPos pos = new BlockPos(xPositions[i], yPositions[i], zPositions[i]);
            newBoundingBlocks.add(pos);
        }
        boundingBlocks = newBoundingBlocks;
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