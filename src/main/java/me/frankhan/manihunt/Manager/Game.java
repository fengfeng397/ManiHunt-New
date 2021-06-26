package me.frankhan.manihunt.Manager;

import me.frankhan.manihunt.Class.Area;
import me.frankhan.manihunt.Gui.Start;
import me.frankhan.manihunt.ManiHunt;
import me.frankhan.manihunt.Runnable.End;
import me.frankhan.manihunt.Runnable.Revive;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {
    //检测人数是否达标
    public static void CheckFine(Area area) {
        if (area.getSpeederNum() == Math.floor(area.getChecker()) && area.getReadyNum() == area.getWaiter().size() &&
                area.getSpeederNum() >0 && area.getHunterNum() >0) {
            Start(area);
        }
    }
    //游戏开始
    public static void Start(Area area) {
        for (Player p:area.getPlayers()) {
            p.closeInventory();
            p.sendTitle(Color.s("&e&l游戏开始"),Color.s("&a速通者:"+ Start.ps2sl(area.getHunter())));
            area.setState("P");
            if (area.getHunter().contains(p)) {
                p.getInventory().setItem(8,new ItemStack(Material.COMPASS));
            }
            if (area.getSpeeder().contains(p) || area.getHunter().contains(p)) {
                p.setGameMode(GameMode.SURVIVAL);
            }else p.setGameMode(GameMode.SPECTATOR);
        }
    }
    //检测是否该结束
    public static void checkAreaEnd(Area area) {
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
        if (alH == 0) {
            End(area,"S");
        }else if (alS == 0) {
            End(area,"H");
        }

    }
    //玩家死亡处理
    public static void playerDeath(Area area,Player player) {
        Inventory inv = player.getInventory();
        ItemStack[] items = inv.getContents();
        for (ItemStack item:items) {
            Item i = (Item) player.getWorld().spawnEntity(player.getLocation(),EntityType.DROPPED_ITEM);
            i.setItemStack(item);
        }
        player.getInventory().clear();
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(area.getSpawnLocation());
        checkAreaEnd(area);
        if (area.getHunter().contains(player)) {
            Revive.player.put(player, me.frankhan.manihunt.Manager.Area.ReviveCoolDown);
            Revive.area.put(player,area);
        }
    }
    //游戏结束
    public static void End(Area area,String win) {
        if (win.equals("S")) {
            area.sendMessage("&e&l游戏结束\n&e获胜方: &a&l速通者\n&e名单:\n"+Start.ps2sl(area.getSpeeder()));
        }
        if (win.equals("H")) {
            area.sendMessage("&e&l游戏结束\n&e获胜方: &c&l猎 人\n&e名单:\n"+Start.ps2sl(area.getHunter()));
        }
        End.area.put(area,100);
    }
    //游戏重置
    public static void Reset(Area a) {
        for (Player p:a.getPlayers()) {
            p.kickPlayer(Color.s("&e感谢您的游玩\nBy FrankHan\nVersion: 0.1"));
        }
        a.setState("R");
        String name = null;
        List<World> w = new ArrayList<>(a.getWorld());
        for (World world:w){
            if (!world.getName().contains("nether")&&!world.getName().contains("the_end")) {
                name = world.getName();
            }
        }
        Server s = ManiHunt.instance.getServer();
        ManiHunt.instance.getServer().shutdown();
        if (name != null) {
            delFile(new File(name));
            delFile(new File(name+"_nether"));
            delFile(new File(name+"_the_end"));
        }
    }
    //删除文件夹及其文件
    public static void delFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    delFile(files[i]);
                }
            }
            file.delete();
        }
    }
    //增加积分
    public static void addScore(Player p,Integer s) {

    }
    //增加积分
    public static void subScore(Player p,Integer s) {

    }
}
