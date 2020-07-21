package com.kittykitcatcat.kittysmodulardoors.init;

import com.kittykitcatcat.kittysmodulardoors.KittysModularDoorsMod;
import com.kittykitcatcat.kittysmodulardoors.content.DoorCreationStationRenderer;
import com.kittykitcatcat.kittysmodulardoors.content.LogicBlockRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = KittysModularDoorsMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BindRenderers
{
    @SubscribeEvent
    public static void bindTERs(FMLClientSetupEvent event)
    {
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.logic_tile_entity, LogicBlockRenderer::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.door_creation_station_tile_entity, DoorCreationStationRenderer::new);
    }
}