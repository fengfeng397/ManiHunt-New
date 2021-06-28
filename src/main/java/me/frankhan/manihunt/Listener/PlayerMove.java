package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Manager.Area;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class PlayerMove implements Listener {
    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (Objects.requireNonNull(Area.fromWhichArea(p)).getState().equals("P")) {
            if (event.getFrom().getX() != Objects.requireNonNull(event.getTo()).getX() ||
                    event.getFrom().getY() != event.getTo().getY() ||
                    event.getFrom().getZ() != event.getTo().getZ()) {
                event.setCancelled(true);
            }
        }
    }
}
