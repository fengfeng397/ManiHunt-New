package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Class.area;
import me.frankhan.manihunt.Manager.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        area a= me.frankhan.manihunt.Manager.Area.fromWhichArea(p);
        if (a != null) {
            if (a.getState().equals("E") || a.getState().equals("W")) {
                a.delHunter(p);
                a.delSpeeder(p);
                a.delPlayer(p);
            }else if (a.hunterContains(p) || a.speederContains(p)) {
                Game.checkAreaEnd(a);
            }
        }
    }
}
