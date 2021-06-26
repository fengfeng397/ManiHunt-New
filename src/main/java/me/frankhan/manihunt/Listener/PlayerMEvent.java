package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Class.Area;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMEvent implements Listener {
    @EventHandler
    public void PlayerMEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!isOK(player)) {
            if (event.getFrom().getX() != event.getTo().getX() ||
                    event.getFrom().getY() != event.getTo().getY() ||
                    event.getFrom().getZ() != event.getTo().getZ()) {
                event.setCancelled(true);
            }
        }
    }

    public static boolean isOK(Player player) {
        for (Area a: me.frankhan.manihunt.Manager.Area.Areas) {
            if (a.contains(player)) {
                if (a.getState().equals("P")) {
                    return true;
                }
            }
        }
        return false;
    }
}
