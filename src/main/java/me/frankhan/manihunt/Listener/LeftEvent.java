package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Class.Area;
import me.frankhan.manihunt.Manager.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeftEvent implements Listener {
    @EventHandler
    public void LeftEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Area area = me.frankhan.manihunt.Manager.Area.fromWhichArea(player);
        if (area != null) {
            if (area.getState().equals("E") || area.getState().equals("W")) {
                area.delHunter(player);
                area.delSpeeder(player);
                area.delPlayer(player);
                area.delWaiter(player);
            }else if (area.getWaiter().containsKey(player)) {
                Game.checkAreaEnd(area);
            }
        }
    }
}
