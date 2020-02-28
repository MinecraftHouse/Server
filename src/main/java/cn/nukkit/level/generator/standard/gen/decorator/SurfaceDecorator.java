package cn.nukkit.level.generator.standard.gen.decorator;

import cn.nukkit.level.chunk.IChunk;
import cn.nukkit.level.generator.standard.StandardGeneratorUtils;
import cn.nukkit.level.generator.standard.gen.Decorator;
import cn.nukkit.level.generator.standard.misc.filter.BlockFilter;
import cn.nukkit.level.generator.standard.misc.layer.BlockLayer;
import cn.nukkit.utils.ConfigSection;
import lombok.NonNull;
import net.daporkchop.lib.random.PRandom;

/**
 * Replaces all blocks of a given type with a certain pattern of replacement blocks from the top down.
 *
 * @author DaPorkchop_
 */
public final class SurfaceDecorator implements Decorator {
    private final BlockFilter  target;
    private final BlockLayer[] layers;

    public SurfaceDecorator(@NonNull ConfigSection config, @NonNull PRandom random) {
        this.target = StandardGeneratorUtils.parseBlockChecker(config.getString("target", "stone"));
        this.layers = StandardGeneratorUtils.parseBlockLayers(config.getString("blocks")).toArray(new BlockLayer[0]);
    }

    @Override
    public void decorate(IChunk chunk, PRandom random, int x, int z) {
        for (int prevId = 0, y = 255; y >= 0; y--) {
            int id = chunk.getBlockRuntimeIdUnsafe(x, y, z, 0);
            if (prevId == 0 && this.target.test(id)) {
                LAYERS:
                for (BlockLayer layer : this.layers) {
                    for (int replaceId = layer.blockId(), i = layer.size(random) - 1; y >= 0 && i >= 0; i--) {
                        chunk.setBlockRuntimeIdUnsafe(x, y, z, 0, replaceId);
                        if (!this.target.test(id = chunk.getBlockRuntimeIdUnsafe(x, --y, z, 0))) {
                            break LAYERS;
                        }
                    }
                }
            }
            prevId = id;
        }
    }
}