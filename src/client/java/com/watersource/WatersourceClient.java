package com.watersource;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class WatersourceClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WATER_SOURCE_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFINITE_WATER_CAULDRON_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFINITE_WATER_CAULDRON_BLOCK, RenderLayer.getCutout());

    }
}
