package com.kittykitcatcat.kittysmodulardoors.content;

import com.kittykitcatcat.kittysmodulardoors.init.ModBlocks;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import java.util.ArrayList;

import static com.kittykitcatcat.kittysmodulardoors.KittysModularDoorsMod.*;
import static net.minecraft.block.Blocks.*;
import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class LogicBlock extends Block
{
    public LogicBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public boolean hasTileEntity(final BlockState state)
    {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING);
    }


    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {
        return new LogicTileEntity();
    }

    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (!isMoving && !newState.getBlock().equals(ModBlocks.door_logic_block))
        {
            if (worldIn.getTileEntity(pos) instanceof LogicTileEntity)
            {
                LogicTileEntity tileEntity = (LogicTileEntity) worldIn.getTileEntity(pos);
                if (tileEntity.boundingBlocks != null && !tileEntity.boundingBlocks.isEmpty())
                {
                    for (BlockPos boundingPos : tileEntity.boundingBlocks)
                    {
                        worldIn.setBlockState(boundingPos, AIR.getDefaultState());
                    }
                }
            }
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (stack.getTag() != null)
        {
            CompoundNBT nbt = stack.getTag();
            int width = nbt.getInt(widthNBT);
            int heigth = nbt.getInt(heightNBT);
            LogicTileEntity tileEntity = (LogicTileEntity) worldIn.getTileEntity(pos);
            tileEntity.width = width;
            tileEntity.height = heigth;
            tileEntity.animationType = nbt.getInt(animationNBT);
            tileEntity.itemID = nbt.getInt(itemNBT);
            for (int i = 0; i < width; i++)
            {
                for (int j = 0; j < heigth; j++)
                {
                    Vector3f direction = state.get(HORIZONTAL_FACING).toVector3f();
                    BlockPos boundingPos = pos.add(i * direction.getX(), j, i * direction.getZ());
                    if (worldIn.getBlockState(boundingPos).getBlock() instanceof AirBlock && !boundingPos.equals(pos))
                    {
                        worldIn.setBlockState(boundingPos, ModBlocks.door_bounding_block.getDefaultState());
                        BoundingTileEntity boundingTileEntity = (BoundingTileEntity) worldIn.getTileEntity(boundingPos);
                        boundingTileEntity.logicBlockPos = pos;
                        if (tileEntity.boundingBlocks == null)
                        {
                            tileEntity.boundingBlocks = new ArrayList<>();
                        }
                        tileEntity.boundingBlocks.add(boundingPos);
                    }
                }
            }
        }
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote())
        {
            if (handIn != Hand.OFF_HAND)
            {

            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
    {
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}