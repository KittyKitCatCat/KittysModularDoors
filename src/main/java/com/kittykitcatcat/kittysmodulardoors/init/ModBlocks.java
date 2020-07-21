package com.kittykitcatcat.kittysmodulardoors.init;

import com.kittykitcatcat.kittysmodulardoors.KittysModularDoorsMod;
import com.kittykitcatcat.kittysmodulardoors.content.BoundingBlock;
import com.kittykitcatcat.kittysmodulardoors.content.DoorCreationStationBlock;
import com.kittykitcatcat.kittysmodulardoors.content.LogicBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
    //functional
    public static Block door_creation_station;
    public static Block door_logic_block;
    public static Block door_bounding_block;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();
        door_bounding_block = registerBlock(registry, new BoundingBlock(Block.Properties.from(Blocks.STONE).noDrops()), "door_bounding_block");
        door_logic_block = registerBlock(registry, new LogicBlock(Block.Properties.from(Blocks.STONE).noDrops()), "door_logic_block");
        door_creation_station = registerBlock(registry, new DoorCreationStationBlock(Block.Properties.from(Blocks.STONE)), "door_creation_station");
    }

    private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name)
    {
        String prefixedName = KittysModularDoorsMod.MODID + ":" + name;
        newBlock.setRegistryName(prefixedName);
        registry.register(newBlock);
        return newBlock;
    }
}
