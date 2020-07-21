package com.kittykitcatcat.kittysmodulardoors;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("kittysmodulardoors")
public class KittysModularDoorsMod
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final  String MODID = "kittysmodulardoors";
    public static String logicBlockPositionNBT = "logic_block_pos_";
    public static String widthNBT = "door_width";
    public static String heightNBT = "door_height";
    public static String itemNBT = "door_material";
    public static String animationNBT = "door_animation";
    public KittysModularDoorsMod()
    {
    }
}
