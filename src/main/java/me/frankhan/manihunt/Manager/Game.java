package me.frankhan.manihunt.Manager;

import me.frankhan.manihunt.Class.area;
import me.frankhan.manihunt.Runnable.End;
import me.frankhan.manihunt.Runnable.Revive;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Game {
    //游戏开始
    public static void Start(area area) {
        //检测游戏是否满足开始条件
        if (area.getSpeederNum() == Math.floor(area.getUS()) &&
                area.getReadyNum() == area.getSpeederNum() + area.getHunterNum() &&
                area.getSpeederNum() >0 &&
                area.getHunterNum() >0) {
            for (Player p:area.getPlayers()) {
                p.closeInventory();
                p.sendTitle(Color.s("&e&l游戏开始"),Color.s("&a速通者:"+ area.getSpeederS()));
                area.setState("P");
                if (area.getHunter().contains(p)) {
                    p.getInventory().setItem(8,new ItemStack(Material.COMPASS));
                }
                if (area.getSpeeder().contains(p) || area.getHunter().contains(p)) {
                    p.setGameMode(GameMode.SURVIVAL);
                }else p.setGameMode(GameMode.SPECTATOR);
            }
        }
    }
    //玩家死亡处理
    public static void playerDeath(area area, Player player) {
        Inventory inv = player.getInventory();
        ItemStack[] items = inv.getContents();
        for (ItemStack item:items) {
            Item i = (Item) player.getWorld().spawnEntity(player.getLocation(),EntityType.DROPPED_ITEM);
            i.setItemStack(item);
        }
        player.getInventory().clear();
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(area.getSpawnLocation());
        End(area,"S");
        if (area.getHunter().contains(player)) {
            Revive.player.put(player, me.frankhan.manihunt.Manager.Area.ReviveCoolDown);
            Revive.area.put(player,area);
        }
    }
    //游戏结束
    public static void End(area area,String win) {
        int alS = 0;
        int alH = 0;
        //统计速通者生还人数
        for (Player player: area.getSpeeder()) {
            if (player.getGameMode() != GameMode.SPECTATOR &&
                    player.getServer().getOnlinePlayers().contains(player)) alS++;
        }
        //统计猎人生还人数
        for (Player player: area.getHunter()) {
            if (player.getGameMode() != GameMode.SPECTATOR &&
                    player.getServer().getOnlinePlayers().contains(player)) alH++;
        }
        if (alH == 0 && win.equals("S")) {
            area.sendMessage("&e&l游戏结束\n&e获胜方: &a&l速通者\n&e名单:\n"+area.getSpeederS());
        }else if (alS == 0 && win.equals("H")) {
            area.sendMessage("&e&l游戏结束\n&e获胜方: &c&l猎 人\n&e名单:\n"+area.getHunterS());
        }
        End.areas.put(area,200);
    }
    //游戏重置
    public static void Reset(area a) {
        for (Player p:a.getPlayers()) {
            p.kickPlayer(Color.s("&e感谢您的游玩\nBy FrankHan\nVersion: 0.1"));
        }
        a.setState("R");
    }
    //增加积分
    public static void addScore(Player p,Integer s) {

    }
    //增加积分
    public static void subScore(Player p,Integer s) {

    }
}
