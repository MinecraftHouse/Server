package org.cloudburstmc.server.event.player;

import org.cloudburstmc.server.block.Block;
import org.cloudburstmc.server.event.HandlerList;
import org.cloudburstmc.server.player.Player;

public class PlayerBedLeaveEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    private final Block bed;

    public PlayerBedLeaveEvent(Player player, Block bed) {
        super(player);
        this.bed = bed;
    }

    public Block getBed() {
        return bed;
    }
}
