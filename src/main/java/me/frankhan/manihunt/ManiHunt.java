package me.frankhan.manihunt;

import me.frankhan.manihunt.Command.CompassCmd;
import me.frankhan.manihunt.Command.StartCmd;
import me.frankhan.manihunt.Listener.*;
import me.frankhan.manihunt.Manager.Area;
import me.frankhan.manihunt.Manager.Main;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;

public final class ManiHunt extends JavaPlugin {
    public static ManiHunt instance;
    public static String version;

    @Override
    public void onEnable() {
        instance = this;
        version = getServer().getBukkitVersion().split("-")[0].split("\\.")[1];
        Main.Load();
        Area.Load();
        if (!new File(getDataFolder(),"config.yml").exists()) {
            saveResource("config.yml",true);
        }
        getServer().getPluginManager().registerEvents(new ClickInvEvent(),this);
        getServer().getPluginManager().registerEvents(new JoinEvent(),this);
        getServer().getPluginManager().registerEvents(new LeftEvent(),this);
        getServer().getPluginManager().registerEvents(new PlayerMEvent(),this);
        getServer().getPluginManager().registerEvents(new DamageEntityEvent(),this);
        getServer().getPluginManager().registerEvents(new DeathEvent(),this);
        getServer().getPluginManager().registerEvents(new InteractEvent(),this);
        Bukkit.getPluginCommand("s").setExecutor(new StartCmd());
        Bukkit.getPluginCommand("c").setExecutor(new CompassCmd());
        BukkitTask task1 = new me.frankhan.manihunt.Runnable.Gui().runTaskTimer(this,0,1);
        BukkitTask task2 = new me.frankhan.manihunt.Runnable.Revive().runTaskTimer(this,0,1);
        BukkitTask task3 = new me.frankhan.manihunt.Runnable.Compass().runTaskTimer(this,0,1);
        BukkitTask task4 = new me.frankhan.manihunt.Runnable.End().runTaskTimer(this,0,1);
    }

    @Override
    public void onDisable() {

    }

    public static void Logger(String s) {
        instance.getLogger().info(Color.s(s));
    }
}
