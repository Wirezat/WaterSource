package com.watersource;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<InfiniteWaterCauldronBlockEntity> INFINITE_WATER_CAULDRON_BLOCK_ENTITY;

    public static void registerBlockEntities() {
        INFINITE_WATER_CAULDRON_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Watersource.MOD_ID, "infinite_water_cauldron_block_entity"),
            BlockEntityType.Builder.create(InfiniteWaterCauldronBlockEntity::new, ModBlocks.INFINITE_WATER_CAULDRON_BLOCK).build(null)
        );
    }
}
