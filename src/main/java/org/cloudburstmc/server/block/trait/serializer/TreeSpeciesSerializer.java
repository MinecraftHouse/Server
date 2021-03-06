package org.cloudburstmc.server.block.trait.serializer;

import org.cloudburstmc.server.block.BlockState;
import org.cloudburstmc.server.block.BlockTraits;
import org.cloudburstmc.server.block.BlockTypes;
import org.cloudburstmc.server.block.trait.BlockTrait;
import org.cloudburstmc.server.block.trait.BlockTraitSerializers.TraitSerializer;
import org.cloudburstmc.server.utils.Identifier;
import org.cloudburstmc.server.utils.data.TreeSpecies;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.cloudburstmc.server.block.serializer.util.BedrockStateTags.*;

@ParametersAreNonnullByDefault
public class TreeSpeciesSerializer implements TraitSerializer<TreeSpecies> {

    private static final String[] BEDROCK_LOG_TRAITS = {
            TAG_OLD_LOG_TYPE,
            TAG_NEW_LOG_TYPE
    };

    private static final String[] BEDROCK_LEAF_TRAITS = {
            TAG_OLD_LEAF_TYPE,
            TAG_NEW_LEAF_TYPE
    };

    @Override
    public String getName(BlockState state, BlockTrait<?> blockTrait) {
        Identifier type = state.getType();

        if (type == BlockTypes.PLANKS) {
            return "wood_type";
        }

        if (type == BlockTypes.SAPLING) {
            return "sapling_type";
        }

        if (type == BlockTypes.BAMBOO_SAPLING) {
            return "sapling_type";
        }

        int index = state.ensureTrait(BlockTraits.TREE_SPECIES).ordinal() >> 2;

        if (type == BlockTypes.LOG) {
            return BEDROCK_LOG_TRAITS[index];
        }

        if (type == BlockTypes.LEAVES) {
            return BEDROCK_LEAF_TRAITS[index];
        }

        return "wood_type";
    }
}
