package me.frankhan.manihunt.Manager;

import me.frankhan.manihunt.Class.area;
import me.frankhan.manihunt.ManiHunt;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.util.*;

public class Area {
    public static int maxSpeeder;
    public static int maxHunter;
    public static boolean Totem_of_Undying;
    public static int SKill;
    public static int SDeath;
    public static int SAchievement;
    public static int HKill;
    public static int HDeath;
    public static int HAchievement;
    public static int ReviveCoolDown;
    public static double advance;
    public static List<String> Names = new ArrayList<>();
    public static List<area> Areas = new ArrayList<>();
    public static void Load() {
        //从配置文件读取数据
        Names = getStrList("Area.Name");
        maxSpeeder = getInt("Area.maxSpeeder");
        maxHunter = getInt("Area.maxHunter");
        SKill = getInt("Area.Speeder.Kill");
        SDeath = getInt("Area.Speeder.Death");
        SAchievement = getInt("Area.Speeder.Advancement");
        HKill = getInt("Area.Hunter.Kill");
        HDeath = getInt("Area.Hunter.Death");
        HAchievement = getInt("Area.Hunter.Advancement");
        ReviveCoolDown = getInt("Area.Hunter.ReviveCooldown(tick)");
        Totem_of_Undying = getBoolean("Area.Totem_of_Undying");
        advance = ManiHunt.instance.getConfig().getDouble("Area.advance");
        //如果Name下有世界名则加载世界
        if (!Names.isEmpty()) {
            for (String s: Names) {
                World world = ManiHunt.instance.getServer().createWorld(new WorldCreator(s).environment(World.Environment.NORMAL));
                World nether = ManiHunt.instance.getServer().createWorld(new WorldCreator(s+"_nether").environment(World.Environment.NETHER));
                World theEnd = ManiHunt.instance.getServer().createWorld(new WorldCreator(s+"_the_end").environment(World.Environment.THE_END));
                safeSL(world);
                Set<World> worlds = new HashSet<>(Arrays.asList(world,nether,theEnd));
                area a = new area(s,worlds,"E",new HashSet<>(),new HashSet<>(),new HashSet<>(),0.0,new HashMap<>());
                Areas.add(a);
            }
        }
    }
    public static void safeSL(World w) {
        if (w.getBlockAt(w.getSpawnLocation()).getType() != Material.AIR &&
                w.getBlockAt(w.getSpawnLocation().add(0,1,0)).getType() != Material.AIR) {
            w.setSpawnLocation(w.getSpawnLocation().add(0,1,0));
            safeSL(w);
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
    public static area fromWhichArea(Player player) {
        for (area area: Area.Areas) {
            if (area.playerContains(player)) {
                return area;
            }
        }
        return null;
    }
}
