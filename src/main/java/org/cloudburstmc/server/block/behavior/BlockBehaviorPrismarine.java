package org.cloudburstmc.server.block.behavior;

import org.cloudburstmc.server.block.Block;
import org.cloudburstmc.server.block.BlockTraits;
import org.cloudburstmc.server.item.Item;
import org.cloudburstmc.server.item.ItemTool;
import org.cloudburstmc.server.utils.BlockColor;

public class BlockBehaviorPrismarine extends BlockBehaviorSolid {

    @Override
    public float getHardness() {
        return 1.5f;
    }

    @Override
    public float getResistance() {
        return 30;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public Item[] getDrops(Block block, Item hand) {
        if (hand.isPickaxe() && hand.getTier() >= ItemTool.TIER_WOODEN) {
            return new Item[]{
                    toItem(block)
            };
        } else {
            return new Item[0];
        }
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public BlockColor getColor(Block block) {
        switch (block.getState().ensureTrait(BlockTraits.PRISMARINE_BLOCK_TYPE)) {
            case DEFAULT:
                return BlockColor.CYAN_BLOCK_COLOR;
            case BRICKS:
            case DARK:
                return BlockColor.DIAMOND_BLOCK_COLOR;
            default:
                return BlockColor.STONE_BLOCK_COLOR;
        }
    }
}
