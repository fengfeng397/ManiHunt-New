package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Class.Area;
import me.frankhan.manihunt.Gui.Compass;
import me.frankhan.manihunt.Gui.Start;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class InteractEvent implements Listener {
    public static Map<Player,Player> compass = new HashMap<>();
    @EventHandler
    public void InteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.hasItem()) {
                Area a = me.frankhan.manihunt.Manager.Area.fromWhichArea(p);
                PlayerInventory inv = p.getInventory();
                if (a != null) {
                    if (a.getState().equals("W")) {
                        Start.StartGUINow(p,a);
                    }else if (a.getState().equals("P") && inv.getItemInMainHand().getType() == Material.COMPASS) {
                        e.setCancelled(true);
                        if (a.getHunter().contains(p)) {
                            Compass.Compass(a,p);
                        }
                    }else e.setCancelled(false);
                }
            }
        }else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Area a = me.frankhan.manihunt.Manager.Area.fromWhichArea(p);
            if (a != null) {
                if (!a.getState().equals("P")) {
                    e.setCancelled(true);
                }
            }
        }else e.setCancelled(false);
    }
}
