package me.frankhan.manihunt.Runnable;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Gui extends BukkitRunnable {
    public static Map<Inventory,Integer> tasks = new HashMap<>();
    public static Map<Inventory,Player> dic= new HashMap<>();
    @Override
    public void run() {
        if (!tasks.isEmpty()) {
            for (Inventory gui: tasks.keySet()) {
                tasks.put(gui,tasks.get(gui) -1);
                if (tasks.get(gui) == 0) {
                    dic.get(gui).openInventory(gui);
                    tasks.remove(gui);
                    dic.remove(gui);
                }
            }
        }
    }
}
