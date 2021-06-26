package me.frankhan.manihunt.Runnable;

import me.frankhan.manihunt.Class.Area;
import me.frankhan.manihunt.Listener.InteractEvent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashSet;
import java.util.Set;

public class Compass extends BukkitRunnable {
    public static Set<Area> areas = new HashSet<>();
    @Override
    public void run() {
        if (!areas.isEmpty()) {
            for (Area a:areas) {
                for (Player p:a.getHunter()) {
                    Player p2 = InteractEvent.compass.get(p);
                    Location loc = p2.getLocation();
                    if (p2.getWorld().getEnvironment() == World.Environment.NETHER) {
                        p.setCompassTarget(new Location(p.getWorld(),loc.getX()/8,loc.getY()/8,loc.getZ()/8));
                    }else {
                        p.setCompassTarget(new Location(p.getWorld(),loc.getX(),loc.getY(),loc.getZ()));
                    }

                }
            }
        }
    }
}
