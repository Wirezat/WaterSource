package com.watersource;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {

    public static final InfiniteWaterCauldronBlock INFINITE_WATER_CAULDRON_BLOCK = registerBlock("infinite_water_cauldron",
        new InfiniteWaterCauldronBlock(AbstractBlock.Settings
            .create()
            .strength(3.0f, 3600000.0f)
            .requiresTool()
            .nonOpaque()
            .ticksRandomly()
            .luminance(state -> 5)
            .sounds(BlockSoundGroup.METAL)
            ));

    private static <T extends Block> T registerBlock(String name, T block) {
        return Registry.register(Registries.BLOCK, new Identifier(Watersource.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        ModBlockEntities.registerBlockEntities();
        Watersource.LOGGER.info("Blocks and BlockEntities registered for " + Watersource.MOD_ID);
    }
}
