package com.kittykitcatcat.kittysmodulardoors.content;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.state.properties.BlockStateProperties.LIT;

public class DoorCreationStationBlock extends Block
{
    public DoorCreationStationBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(LIT, false));
    }

    @Override
    public boolean hasTileEntity(final BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {
        return new DoorCreationStationTileEntity();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.up()).getBlock().equals(Blocks.AIR);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> blockStateBuilder)
    {
        blockStateBuilder.add(HORIZONTAL_FACING);
        blockStateBuilder.add(LIT);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(LIT, false);
    }
    //endregion
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
    {
        return state.get(LIT) ? 12 : 2;
    }

    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {/*
        if (!isMoving && !newState.getBlock().equals(ModBlocks.spirit_furnace))
        {
            if (worldIn.getTileEntity(pos) instanceof SpiritFurnaceBottomTileEntity)
            {
                SpiritFurnaceBottomTileEntity tileEntity = (SpiritFurnaceBottomTileEntity) worldIn.getTileEntity(pos);
                if (tileEntity.inventory.getStackInSlot(0) != ItemStack.EMPTY)
                {
                    Entity entity = new ItemEntity(worldIn, pos.getX() + 0.5f, pos.getY() + 0.9f, pos.getZ() + 0.5f, tileEntity.inventory.getStackInSlot(0).copy());
                    worldIn.addEntity(entity);
                }
            }
        }*/
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote())
        {
            if (handIn != Hand.OFF_HAND)
            {/*
                if (worldIn.getTileEntity(pos) instanceof SpiritFurnaceBottomTileEntity)
                {
                    SpiritFurnaceBottomTileEntity furnaceTileEntity = (SpiritFurnaceBottomTileEntity) worldIn.getTileEntity(pos);
                    boolean success = MalumHelper.stackRestrictedItemTEHandling(player, handIn, ModItems.spirit_charcoal, player.getHeldItemMainhand(), furnaceTileEntity.inventory, 0);
                    if (success)
                    {
                        player.world.notifyBlockUpdate(pos, state, state, 3);
                        player.swingArm(handIn);
                        return ActionResultType.SUCCESS;
                    }
                }*/
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {
        //worldIn.setBlockState(pos.up(), ModBlocks.spirit_furnace_top.getDefaultState().with(HORIZONTAL_FACING, state.get(HORIZONTAL_FACING)));
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
    {
        worldIn.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}