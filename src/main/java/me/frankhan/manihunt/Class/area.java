package me.frankhan.manihunt.Class;

import me.frankhan.manihunt.Manager.Area;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import java.util.*;

public class area {
    private String Name;
    private Set<World> Worlds;
    private String State;
    private Set<Player> Players;
    private Set<Player> Speeder;
    private Set<Player> Hunter;
    private double US;
    private Map<Player,Boolean> Ready;
    //四个状态：E空闲，W等待，P游戏，R重置

    public area(String name, Set<World> worlds, String state, Set<Player> players, Set<Player> speeder, Set<Player> hunter, double US, Map<Player, Boolean> ready) {
        Name = name;
        Worlds = worlds;
        State = state;
        Players = players;
        Speeder = speeder;
        Hunter = hunter;
        this.US = US;
        Ready = ready;
    }

    public boolean speederContains(Player p) {
        return Speeder.contains(p);
    }

    public boolean hunterContains(Player p) {
        return Hunter.contains(p);
    }

    public boolean playerContains(Player p) {
        return Players.contains(p);
    }

    public boolean worldsContains(World w) {
        return Worlds.contains(w);
    }

    public String getState() {
        return State;
    }

    public void setState(String s) {
        State = s;
    }

    public Set<Player> getSpeeder() {
        return Speeder;
    }

    public Set<Player> getHunter() {
        return Hunter;
    }

    public Set<Player> getPlayers() {
        return Players;
    }

    public double getUS() {
        return US;
    }

    public String getSpeederS() {
        List<String> l = new ArrayList<>();
        for (Player p:Speeder) {
            l.add(p.getDisplayName());
        }
        return l.toString();
    }

    public String getHunterS() {
        List<String> l = new ArrayList<>();
        for (Player p:Hunter) {
            l.add(p.getDisplayName());
        }
        return l.toString();
    }

    public String getPlayerS() {
        List<String> l = new ArrayList<>();
        for (Player p:Players) {
            l.add(p.getDisplayName());
        }
        return l.toString();
    }

    public String getReadyS() {
        List<String> l = new ArrayList<>();
        for (Player p:Ready.keySet()) {
            if (Ready.get(p)) {
                l.add(p.getDisplayName());
            }

        }
        return l.toString();
    }

    public void addSpeeder(Player p) {
        Speeder.add(p);
        Ready.put(p,false);
    }

    public void delSpeeder(Player p) {
        Speeder.remove(p);
        Ready.remove(p);
    }

    public void addHunter(Player p) {
        Hunter.add(p);
        Ready.put(p,false);
    }

    public void delHunter(Player p) {
        Hunter.remove(p);
        Ready.remove(p);
    }

    public void addPlayer(Player p) {
        Players.add(p);
    }

    public void delPlayer(Player p) {
        Players.remove(p);
    }

    public void changeReady(Player p) {
        Ready.put(p,!Ready.get(p));
    }

    public boolean getReady(Player p) {
        return Ready.get(p);
    }

    public int getSpeederNum() {
        return Speeder.size();
    }

    public int getHunterNum() {
        return Hunter.size();
    }

    public int getReadyNum() {
        int i = 0;
        for (Player p:Ready.keySet()) {
            if (Ready.get(p)) {
                i++;
            }
        }
        return i;
    }

    public void sendMessage(String s) {
        for (Player p: Players) {
            p.sendMessage(Color.s(s));
        }
    }

    public void updateUS(double maxH,double maxS) {
        US = Hunter.size()/maxH*maxS+Area.advance;
    }

    public Location getSpawnLocation() {
        for (World w:Worlds) {
            if (!w.getName().equals("_nether") && !w.equals("_the_end")) {
                return w.getSpawnLocation();
            }
        }
        return null;
    }

    public double TryUS(double maxH,double maxS,double h) {
        return (h+1)/maxH*maxS+ Area.advance;
    }
}
