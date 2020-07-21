package com.kittykitcatcat.kittysmodulardoors.init;

import com.kittykitcatcat.kittysmodulardoors.content.BoundingTileEntity;
import com.kittykitcatcat.kittysmodulardoors.content.DoorCreationStationTileEntity;
import com.kittykitcatcat.kittysmodulardoors.content.LogicTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities
{
    @ObjectHolder("malum:door_creation_station_tile_entity")
    public static TileEntityType<DoorCreationStationTileEntity> door_creation_station_tile_entity;

    @ObjectHolder("malum:bounding_tile_entity")
    public static TileEntityType<BoundingTileEntity> bounding_tile_entity;

    @ObjectHolder("malum:logic_tile_entity")
    public static TileEntityType<LogicTileEntity> logic_tile_entity;

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> e)
    {
        e.getRegistry().registerAll(
                TileEntityType.Builder.create((Supplier<TileEntity>) DoorCreationStationTileEntity::new, ModBlocks.door_creation_station).build(null).setRegistryName("door_creation_station_tile_entity"),
                TileEntityType.Builder.create((Supplier<TileEntity>) BoundingTileEntity::new, ModBlocks.door_bounding_block).build(null).setRegistryName("bounding_tile_entity"),
                TileEntityType.Builder.create((Supplier<TileEntity>) LogicTileEntity::new, ModBlocks.door_logic_block).build(null).setRegistryName("logic_tile_entity")
        );
    }
}