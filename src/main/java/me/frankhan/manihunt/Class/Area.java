package me.frankhan.manihunt.Class;

import me.frankhan.manihunt.Tools.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Area {
    private Set<World> world;
    private String state;
    private Set<Player> Players;
    private Set<Player> Speeder;
    private Set<Player> Hunter;
    private Map<Player,Boolean> Waiter;
    private double checker;
    //四个状态：E空闲，W等待，P游戏，R重置


    public Area(Set<World> world, String state, Set<Player> players, Set<Player> speeder, Set<Player> hunter, Map<Player,Boolean> waiter, double checker) {
        this.world = world;
        this.state = state;
        Players = players;
        Speeder = speeder;
        Hunter = hunter;
        Waiter = waiter;
        this.checker = checker;
    }

    public double getChecker() {
        return checker;
    }

    public void setChecker(double checker) {
        this.checker = checker;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<World> getWorld() {
        return world;
    }

    public void setWorld(Set<World> world) {
        this.world = world;
    }

    public Set<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(Set<Player> players) {
        Players = players;
    }

    public Set<Player> getSpeeder() {
        return Speeder;
    }

    public void setSpeeder(Set<Player> speeder) {
        Speeder = speeder;
    }

    public Set<Player> getHunter() {
        return Hunter;
    }

    public void setHunter(Set<Player> hunter) {
        Hunter = hunter;
    }

    public Map<Player,Boolean> getWaiter() {
        return Waiter;
    }

    public void setWaiter(Map<Player,Boolean> waiter) {
        Waiter = waiter;
    }

    public int getSpeederNum() {
        return Speeder.size();
    }

    public int getHunterNum() {
        return Hunter.size();
    }

    public void addSpeeder(Player player) {
        Speeder.add(player);
    }

    public void addHunter(Player player) {
        Hunter.add(player);
    }

    public void addWaiter(Player player) {
        Waiter.put(player,false);
    }

    public void addPlayer(Player player) {
        Players.add(player);
    }

    public void delSpeeder(Player player) {
        Speeder.remove(player);
    }

    public void delHunter(Player player) {
        Hunter.remove(player);
    }

    public void delWaiter(Player player) {
        Waiter.remove(player);
    }

    public void delPlayer(Player player) {
        Players.remove(player);
    }

    public boolean contains(Player player) {
        return Players.contains(player);
    }

    public void changeReady(Player player) {
        if (Waiter.get(player)) {
            Waiter.put(player,false);
        }else {
            Waiter.put(player,true);
        }
    }

    public boolean getReady(Player player) {
        return Waiter.get(player);
    }

    public Set<Player> whoReady() {
        Set<Player> result = new HashSet<>();
        for (Player player: Waiter.keySet()) {
            if (Waiter.get(player)) {
                result.add(player);
            }
        }
        return result;
    }

    public int getReadyNum() {
        int i = 0;
        for (Player player: Waiter.keySet()) {
            if (Waiter.get(player)) {
                i++;
            }
        }
        return i;
    }

    public void sendMessage(String s) {
        for (Player p:Players) {
            p.sendMessage(Color.s(s));
        }
    }

    public Location getSpawnLocation() {
        for (World w:world) {
            if (!w.getName().contains("nether") && !w.getName().contains("the_end")) {
                return w.getSpawnLocation();
            }
        }
        return null;
    }
}
