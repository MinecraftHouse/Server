package org.cloudburstmc.server.block.behavior;

import org.cloudburstmc.server.block.Block;
import org.cloudburstmc.server.block.BlockTraits;
import org.cloudburstmc.server.item.Item;
import org.cloudburstmc.server.item.ItemTool;
import org.cloudburstmc.server.utils.BlockColor;
import org.cloudburstmc.server.utils.data.DyeColor;

public class BlockBehaviorConcrete extends BlockBehaviorSolid {

    @Override
    public float getResistance() {
        return 9;
    }

    @Override
    public float getHardness() {
        return 1.8f;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public Item[] getDrops(Block block, Item hand) {
        return hand.getTier() >= ItemTool.TIER_WOODEN ? new Item[]{toItem(block)} : new Item[0];
    }

    @Override
    public BlockColor getColor(Block block) {
        return getDyeColor(block).getColor();
    }

    public DyeColor getDyeColor(Block block) {
        return block.getState().ensureTrait(BlockTraits.COLOR);
    }
}
