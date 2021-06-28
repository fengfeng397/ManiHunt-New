package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Gui.Start;
import me.frankhan.manihunt.ManiHunt;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void JoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        World w = p.getWorld();
        for (me.frankhan.manihunt.Class.area area: me.frankhan.manihunt.Manager.Area.Areas) {
            //玩家所在世界是Area
            if (area.worldsContains(w)) {
                ManiHunt.Logger(area.getState());
                if (area.getState().equals("E")) {
                    //空闲状态
                    area.setState("W");
                    area.addPlayer(p);
                    Start.StartGUI(p,area,0);
                    p.setBedSpawnLocation(p.getLocation());
                }else if (area.getState().equals("W")) {
                    //等待状态
                    area.addPlayer(p);
                    Start.StartGUI(p,area,0);
                    p.setBedSpawnLocation(p.getLocation());
                }else if (area.getState().equals("P")) {
                    //游戏状态
                    if (!area.playerContains(p)) {
                        p.setGameMode(GameMode.SPECTATOR);
                        area.addPlayer(p);
                    }else p.sendMessage(Color.s("[ManiHunt] &a重连成功"));
                }else if (area.getState().equals("R")) {
                    //重置状态
                    p.kickPlayer("服务器正在重置");
                }

            }
        }
    }
}
