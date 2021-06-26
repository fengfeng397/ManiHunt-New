package me.frankhan.manihunt.Manager;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import me.frankhan.manihunt.Class.Rank;
import me.frankhan.manihunt.ManiHunt;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static String Mode;
    public static List<Rank> Ranks;
    public static Map<Rank,List<Entity>> RanksE;

    public static void Load() {
        //获取插件运行模式
        Mode = getString("Mode");
        if (!Objects.equals(Mode, "Area") && !Objects.equals(Mode, "New")) {
            //读取以及生成大厅的Rank
            loadRank();
            summonRanks();
        }
    }

    public static String getString(String s) {
        if (ManiHunt.instance.getConfig().contains(s)) {
            return ManiHunt.instance.getConfig().getString(s);
        }else {
            ManiHunt.Logger("&c配置文件中没有名为"+s+"的键![String]");
            return null;
        }
    }
    public static Double getDouble(String d) {
        if (ManiHunt.instance.getConfig().contains(d)) {
            return ManiHunt.instance.getConfig().getDouble(d);
        }else {
            ManiHunt.Logger("&c配置文件中没有名为"+d+"的键![Double]");
            return null;
        }
    }
    public static void loadRank() {
        //判断是否存在Rank键值对
        if (ManiHunt.instance.getConfig().contains("Lobby.Rank")) {
            //存放RankID的List
            List<String> ranks = ManiHunt.instance.getConfig().getStringList("Lobby.Rank");
            //用循环读取并写入成Rank类
            for (String rank: ranks) {
                String world = getString("Lobby.Rank." + rank + ".World");
                Double x = getDouble("Lobby.Rank."+rank+".X");
                Double y = getDouble("Lobby.Rank."+rank+".Y");
                Double z = getDouble("Lobby.Rank."+rank+".Z");
                //如果成分不残缺就加入List
                if (world != null && x != null && y != null && z != null) {
                    World World = ManiHunt.instance.getServer().getWorld(world);
                    Rank Rank = new Rank(World,x,y,z);
                    Ranks.add(Rank);
                }else ManiHunt.Logger("&c配置文件中Lobby.Rank." + rank + "成分残缺");
            }
        }
    }
    public static void summonRanks() {
        for (Rank rank : Ranks) {
            //后续将使用多个盔甲架显示更多内容
            //当前仅用于测试
            List<Entity> rl = new ArrayList<>();
            Location loc = new Location(rank.getWorld(),rank.getX(),rank.getY(),rank.getZ());
            entityClean(rank.getWorld(),loc,EntityType.ARMOR_STAND);
            ArmorStand armorStand = (ArmorStand) rank.getWorld().spawnEntity(loc,EntityType.ARMOR_STAND);
            NBTEntity nbtEnt = new NBTEntity(armorStand);
            nbtEnt.setString("CustomName", Color.s("&e&l排行榜&a测试"));
            nbtEnt.setByte("Invulnerable",(byte)1);
            nbtEnt.setByte("NoGravity",(byte)1);
            nbtEnt.setByte("Invisible",(byte)1);
            nbtEnt.setByte("Marker",(byte)1);
            rl.add(armorStand);
            RanksE.put(rank,rl);
        }

    }
    public static void entityClean(World world,Location loc,EntityType ent){
        //后续会检测同xz且多个y的盔甲架子，因为或有多个！
        for (Entity entity: world.getEntities()) {
            if (entity.getLocation() == loc && entity.getType() == ent) {
                entity.remove();
            }
        }
    }
}
