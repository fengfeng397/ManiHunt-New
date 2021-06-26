package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Class.Area;
import me.frankhan.manihunt.Manager.Game;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEntityEvent implements Listener {
    @EventHandler
    public void DamageEntityEvent(EntityDamageByEntityEvent event) {
        //如果是玩家之间的攻击
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player Damager = (Player) event.getDamager();
            Player Defender = (Player) event.getEntity();
            Area area = me.frankhan.manihunt.Manager.Area.fromWhichArea(Defender);
            if (area != null){
                //如果area已开始
                if (area.getState().equals("P")) {
                    //如果防御者将死亡
                    if (Defender.getHealth() - event.getFinalDamage() <= 0.0) {
                        //如果防御者是速通者
                        if (area.getSpeeder().contains(Defender)) {
                            area.sendMessage(Color.s("[ManiHunt] &e&l"+Damager.getDisplayName()+"&e击杀了&a&l速通者"+Defender.getDisplayName()+"&e"));
                            event.setCancelled(true);
                            Game.playerDeath(area,Defender);
                            Game.checkAreaEnd(area);
                            Game.addScore(Damager, me.frankhan.manihunt.Manager.Area.HKill);
                            Game.subScore(Defender, me.frankhan.manihunt.Manager.Area.SDeath);
                        //如果防御者是猎人
                        }else if (area.getHunter().contains(Defender)) {
                            event.setCancelled(true);
                            Game.checkAreaEnd(area);
                            Game.playerDeath(area,Defender);
                        }
                    }
                }else event.setCancelled(true);
            }
        }
    }
}
