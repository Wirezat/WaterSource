package com.watersource;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

public class InfiniteWaterCauldronBlock extends Block implements BlockEntityProvider {

    public InfiniteWaterCauldronBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new InfiniteWaterCauldronBlockEntity(pos, state);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {

        if (world.isClient)
            return ActionResult.SUCCESS;

        ItemStack stackInHand = player.getStackInHand(hand);

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof InfiniteWaterCauldronBlockEntity))
            return ActionResult.PASS;

        Storage<FluidVariant> storage = ((InfiniteWaterCauldronBlockEntity) blockEntity).getFluidStorage();
        FluidVariant water = FluidVariant.of(Fluids.WATER);

        ItemStack filledBucket = new ItemStack(Items.WATER_BUCKET);

        if (stackInHand.getItem() == Items.BUCKET) {
            try (var transaction = Transaction.openOuter()) {
                long extracted = storage.extract(water, FluidConstants.BUCKET, transaction);
                if (extracted == FluidConstants.BUCKET) {
                    transaction.commit();
                    if (!player.getAbilities().creativeMode) {
                        stackInHand.decrement(1);
                        player.giveItemStack(filledBucket);
                    }
                    if (world instanceof ServerWorld serverWorld) {
                        ParticleUtils.spawnMysticalParticles(serverWorld, pos);
                    }
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1f, 1f);

                    return ActionResult.SUCCESS;
                }
            }
        }
        if (stackInHand.isOf(Items.WATER_BUCKET)) {
            if (!player.getAbilities().creativeMode) {
                stackInHand.decrement(1);
                player.giveItemStack(new ItemStack(Items.BUCKET));
            }
                if (world instanceof ServerWorld serverWorld) {
                    ParticleUtils.spawnMysticalParticles(serverWorld, pos);
                }
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1f, 1f);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

}
