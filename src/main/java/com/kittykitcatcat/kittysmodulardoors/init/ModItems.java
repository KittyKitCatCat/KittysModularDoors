package com.kittykitcatcat.kittysmodulardoors.init;

import com.google.common.base.Preconditions;
import com.kittykitcatcat.kittysmodulardoors.KittysModularDoorsMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
    static final ItemGroup KITTYS_MODULAR_DOORS_GROUP = new ModItemGroup(KittysModularDoorsMod.MODID, () -> new ItemStack(ModItems.door_creation_station));
    public static final class ModItemGroup extends ItemGroup
    {
        @Nonnull
        private final Supplier<ItemStack> iconSupplier;
        ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier)
        {
            super(name);
            this.iconSupplier = iconSupplier;
        }
        @Override
        @Nonnull
        public ItemStack createIcon()
        {
            return iconSupplier.get();
        }
    }
    //MATERIALS
    public static Item door_creation_station;
    public static Item modular_door;

    public static Item basic_door;

    static Item.Properties basic_properties = new Item.Properties().group(KITTYS_MODULAR_DOORS_GROUP).maxStackSize(64);
    static Item.Properties hidden_properties = new Item.Properties().maxStackSize(1);
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(
                modular_door = setup(new BlockItem(ModBlocks.door_logic_block, basic_properties), "modular_door"),
                door_creation_station = setup(new BlockItem(ModBlocks.door_creation_station, basic_properties), "door_creation_station"),
                basic_door = setup(new Item(hidden_properties), "basic_door")
        );
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name)
    {
        Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
        return setup(entry, new ResourceLocation(KittysModularDoorsMod.MODID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName)
    {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }
}
