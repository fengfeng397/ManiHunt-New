package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Class.Area;
import me.frankhan.manihunt.Manager.Game;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathEvent implements Listener {
    @EventHandler
    public void DeathEvent(EntityDeathEvent e) {
        //如果龙龙死了
        if (e.getEntity().getType() == EntityType.ENDER_DRAGON) {
            World w = e.getEntity().getWorld();
            Area area = null;
            for (Area a : me.frankhan.manihunt.Manager.Area.Areas) {
                if (a.getWorld().contains(w)) {
                    area = a;
                }
            }
            if (area != null) Game.End(area, "S");
        }
    }
}
