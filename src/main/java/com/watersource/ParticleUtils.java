package com.watersource;

import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.joml.Vector3f;

import java.util.Random;

public class ParticleUtils {

    public static void spawnMysticalParticles(ServerWorld world, BlockPos pos) {
        Random random = new Random();

        DustParticleEffect greyDust = new DustParticleEffect(new Vector3f(0.7f, 0.7f, 0.7f), 0.9f);
        DustParticleEffect blueDust = new DustParticleEffect(new Vector3f(0.2f, 0.4f, 1.0f), 0.7f);

        for (int i = 0; i < 5; i++) {
            double face = random.nextDouble();
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.5;
            double z = pos.getZ() + 0.5;

            if (face < 0.25) {
                x += random.nextDouble() - 0.5;
                z += random.nextDouble() - 0.5;
                y += 0.6;
            } else if (face < 0.5) {
                x += random.nextDouble() - 0.5;
                z += random.nextDouble() - 0.5;
                y -= 0.6;
            } else if (face < 0.75) {
                x += 0.6 * (random.nextBoolean() ? 1 : -1);
                y += random.nextDouble() - 0.5;
                z += random.nextDouble() - 0.5;
            } else {
                z += 0.6 * (random.nextBoolean() ? 1 : -1);
                y += random.nextDouble() - 0.5;
                x += random.nextDouble() - 0.5;
            }

            world.spawnParticles(greyDust, x, y, z, 1, 0, 0, 0, 1);
            world.spawnParticles(blueDust, x, y, z, 1, 0, 0, 0, 1);
            world.spawnParticles(blueDust, x, y, z, 1, 0, 0, 0, 1);
            world.spawnParticles(blueDust, x, y, z, 1, 0, 0, 0, 1);
            world.spawnParticles(blueDust, x, y, z, 1, 0, 0, 0, 1);
            world.spawnParticles(blueDust, x, y, z, 1, 0, 0, 0, 1);
        }
    }
}
