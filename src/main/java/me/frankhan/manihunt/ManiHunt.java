package me.frankhan.manihunt;

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
    public static int version;

    @Override
    public void onEnable() {
        instance = this;
        version =  Integer.parseInt(getServer().getBukkitVersion().split("-")[0].split("\\.")[1]);
        Main.Load();
        Main.Run();
        getConfig().options().copyDefaults();
        if (!new File(getDataFolder(),"config.yml").exists()) {
            saveDefaultConfig();
        }
        getServer().getPluginManager().registerEvents(new ClickInv(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(),this);
        getServer().getPluginManager().registerEvents(new PlayerMove(),this);
        getServer().getPluginManager().registerEvents(new EntityDamage(),this);
        getServer().getPluginManager().registerEvents(new EntityDeath(),this);
        getServer().getPluginManager().registerEvents(new Interact(),this);
        Bukkit.getPluginCommand("s").setExecutor(new StartCmd());
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
