package me.frankhan.manihunt.Runnable;

import me.frankhan.manihunt.Class.Area;
import me.frankhan.manihunt.Manager.Game;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class End extends BukkitRunnable {
    public static Map<Area,Integer> area = new HashMap<>();

    @Override
    public void run() {
        if (!area.isEmpty()) {
            for (Area a: area.keySet()) {
                area.put(a,area.get(a)-1);
                if (area.get(a) == 0) {
                    Game.Reset(a);
                    area.remove(a);
                }
            }
        }
    }
}
