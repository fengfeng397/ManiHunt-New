package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Class.area;
import me.frankhan.manihunt.Manager.Game;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
    @EventHandler
    public void DeathEvent(EntityDeathEvent e) {
        //如果龙龙死了
        if (e.getEntity().getType() == EntityType.ENDER_DRAGON) {
            World w = e.getEntity().getWorld();
            area area = null;
            for (me.frankhan.manihunt.Class.area a : me.frankhan.manihunt.Manager.Area.Areas) {
                if (a.worldsContains(w)) {
                    area = a;
                }
            }
            if (area != null) Game.End(area,"S");
        }
    }
}
