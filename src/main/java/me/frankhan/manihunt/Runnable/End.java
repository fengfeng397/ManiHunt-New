package me.frankhan.manihunt.Runnable;

import me.frankhan.manihunt.Class.area;
import me.frankhan.manihunt.Manager.Game;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.Map;

public class End extends BukkitRunnable {
    public static Map<area,Integer> areas = new HashMap<>();

    @Override
    public void run() {
        if (!areas.isEmpty()) {
            for (area a: areas.keySet()) {
                areas.put(a,areas.get(a)-1);
                if (areas.get(a) == 0) {
                    Game.Reset(a);
                    areas.remove(a);
                }
            }
        }
    }
}
