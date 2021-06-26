package me.frankhan.manihunt.Manager;

import me.frankhan.manihunt.ManiHunt;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;

public class Area {
    public static int maxArea;
    public static int maxSpeeder;
    public static int maxHunter;
    public static boolean Elytra;
    public static int ElytraCoolDown;
    public static boolean Totem_of_Undying;
    public static int SKill;
    public static int SDeath;
    public static int SAchievement;
    public static int HKill;
    public static int HDeath;
    public static int HAchievement;
    public static int ReviveCoolDown;
    public static List<String> Worlds = new ArrayList<>();
    public static List<me.frankhan.manihunt.Class.Area> Areas = new ArrayList<>();
    public static void Load() {
        //从配置文件读取数据
        maxArea = getInt("Area.maxArea");
        maxSpeeder = getInt("Area.maxSpeeder");
        maxHunter = getInt("Area.maxHunter");
        Elytra = getBoolean("Area.Elytra");
        ElytraCoolDown = getInt("Area.ElytraCooldown(tick)");
        SKill = getInt("Area.Speeder.Kill");
        SDeath = getInt("Area.Speeder.Death");
        SAchievement = getInt("Area.Speeder.Achievement");
        HKill = getInt("Area.Hunter.Kill");
        HDeath = getInt("Area.Hunter.Death");
        HAchievement = getInt("Area.Hunter.Achievement");
        ReviveCoolDown = getInt("Area.Hunter.ReviveCooldown(tick)");
        Totem_of_Undying = getBoolean("Area.Totem_of_Undying");
        Worlds = getStrList("Area.World");
        ManiHunt.Logger(Worlds.toString());
        //如果World下有世界名则加载世界
        if (Worlds.size() != 0) {
            for (String s: Worlds) {
                World world = ManiHunt.instance.getServer().getWorld(s);
                World nether = ManiHunt.instance.getServer().getWorld(s+"_nether");
                World theEnd = ManiHunt.instance.getServer().getWorld(s+"_the_end");
                if (world != null) safeloc(world);
                Set<World> worlds = new HashSet<>(Arrays.asList(world,nether,theEnd));
                me.frankhan.manihunt.Class.Area area = new me.frankhan.manihunt.Class.Area(worlds,"E",new HashSet<>(),new HashSet<>(),new HashSet<>(),new HashMap<>(),0.0);
                if (Areas.size() <= maxArea +1) {
                    Areas.add(area);
                }else ManiHunt.Logger("因Area数已达到最大Area数，故世界名为"+s+"的世界不会被作为Area");
            }
        }
    }
    public static void safeloc(World w) {
        if(w.getBlockAt(w.getSpawnLocation()).getType() != Material.AIR &&
                w.getBlockAt(w.getSpawnLocation().add(0,1,0)).getType() != Material.AIR) {
            w.setSpawnLocation(w.getSpawnLocation().add(0,2,0));
            safeloc(w);
        }
    }
    public static boolean getBoolean(String s) {
        if (ManiHunt.instance.getConfig().contains(s)) {
            return ManiHunt.instance.getConfig().getBoolean(s);
        }else {
            ManiHunt.Logger("&c配置文件中没有名为"+s+"的键![String]");
            return false;
        }
    }
    public static int getInt(String s) {
        if (ManiHunt.instance.getConfig().contains(s)) {
            return ManiHunt.instance.getConfig().getInt(s);
        }else {
            ManiHunt.Logger("&c配置文件中没有名为"+s+"的键![String]");
            return -1;
        }
    }
    public static List<String> getStrList(String s) {
        if (ManiHunt.instance.getConfig().contains(s)) {
            return ManiHunt.instance.getConfig().getStringList(s);
        }else {
            ManiHunt.Logger("&c配置文件中没有名为"+s+"的键![String]");
            return new ArrayList<>();
        }
    }
    public static void updateChecker(me.frankhan.manihunt.Class.Area area) {
        double s = area.getSpeeder().size();
        double h = area.getHunter().size();
        area.setChecker(h/maxHunter*maxSpeeder+0.5);
    }
    public static me.frankhan.manihunt.Class.Area fromWhichArea(Player player) {
        for (me.frankhan.manihunt.Class.Area area: me.frankhan.manihunt.Manager.Area.Areas) {
            if (area.contains(player)) {
                return area;
            }
        }
        return null;
    }
}
