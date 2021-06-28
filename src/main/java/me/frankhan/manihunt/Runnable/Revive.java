package me.frankhan.manihunt.Runnable;

import me.frankhan.manihunt.Tools.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Revive extends BukkitRunnable {
    public static Map<Player,Integer> player = new HashMap<>();
    public static Map<Player, me.frankhan.manihunt.Class.area> area = new HashMap<>();
    @Override
    public void run() {
        if (!player.isEmpty()) {
            for (Player p: player.keySet()) {
                player.put(p,player.get(p)-1);
                if (player.get(p) == 0) {
                    p.teleport(p.getWorld().getSpawnLocation());
                    p.setGameMode(GameMode.SURVIVAL);
                    p.setHealth(20.0);
                    p.setFoodLevel(20);
                    p.getInventory().setItem(8,new ItemStack(Material.COMPASS));
                    p.sendMessage(Color.s("[ManiHunt] &a您已重生!"));
                }
            }
        }
    }
}
