package com.watersource;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item INFINITE_WATER_CAULDRON_ITEM = new BlockItem(ModBlocks.INFINITE_WATER_CAULDRON_BLOCK, new Item.Settings());

    public static void registerModItems() {
        Registry.register(Registries.ITEM, new Identifier(Watersource.MOD_ID, "infinite_water_cauldron"), INFINITE_WATER_CAULDRON_ITEM);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(INFINITE_WATER_CAULDRON_ITEM);
        });
    }
}
