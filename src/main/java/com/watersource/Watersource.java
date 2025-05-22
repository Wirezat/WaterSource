package com.watersource;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;

public class Watersource implements ModInitializer {

    public static final String MOD_ID = "watersource";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        registerFluidStorage();
        LOGGER.info("Watersource mod initialized.");
    }

    private void registerFluidStorage() {
        FluidStorage.SIDED.registerForBlockEntity(
                (InfiniteWaterCauldronBlockEntity blockEntity, net.minecraft.util.math.Direction direction) -> blockEntity
                        .getFluidStorage(),
                ModBlockEntities.INFINITE_WATER_CAULDRON_BLOCK_ENTITY);
    }

}
