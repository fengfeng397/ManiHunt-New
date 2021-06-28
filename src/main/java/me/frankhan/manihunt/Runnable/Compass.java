package me.frankhan.manihunt.Runnable;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Compass extends BukkitRunnable {
    Map<Player, Player> who = new HashMap<>();
    @Override
    public void run() {
        if (!who.isEmpty()) {
        }
    }
}
