package com.watersource;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.fluid.Fluids;
import java.util.Collections;
import java.util.Iterator;

public class InfiniteWaterCauldronBlockEntity extends BlockEntity {

    private static final FluidVariant WATER = FluidVariant.of(Fluids.WATER);

    public InfiniteWaterCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INFINITE_WATER_CAULDRON_BLOCK_ENTITY, pos, state);
    }

    public Storage<FluidVariant> getFluidStorage() {
        return new Storage<FluidVariant>() {
            @Override
            public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
                if (!resource.equals(WATER))
                    return 0;
                return maxAmount;
            }

            @Override
            public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
                if (!resource.equals(WATER))
                    return 0;
                return maxAmount;
            }

            private final StorageView<FluidVariant> waterView = new StorageView<>() {
                @Override
                public FluidVariant getResource() {
                    return WATER;
                }

                @Override
                public boolean isResourceBlank() {
                    return false;
                }

                @Override
                public long getAmount() {
                    return Long.MAX_VALUE;
                }

                @Override
                public long getCapacity() {
                    return Long.MAX_VALUE;
                }

                @Override
                public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
                    if (!resource.equals(WATER))
                        return 0;
                    return maxAmount;
                }
            };

            @Override
            public Iterator<StorageView<FluidVariant>> iterator() {
                return Collections.singleton(waterView).iterator();
            }
        };
    }
}
