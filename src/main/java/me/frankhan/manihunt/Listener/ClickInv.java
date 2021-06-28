package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Class.area;
import me.frankhan.manihunt.Gui.Start;
import me.frankhan.manihunt.Manager.Area;
import me.frankhan.manihunt.Manager.Game;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickInv implements Listener {
    @EventHandler
    public void ClickInvEvent(InventoryClickEvent event) {
         Player player = (Player) event.getWhoClicked();
         World w = player.getWorld();
         if (event.getView().getTitle().startsWith(Color.s("&6&l准备开始!"))) {
             event.setCancelled(true);
             try{
                 area area = null;
                 for (area a: Area.Areas) {
                     if (a.worldsContains(w)) {
                         area = a;
                     }
                 }
                 if (area != null){
                     if (event.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                         if (area.getUS() > area.getSpeederNum() &&
                                 area.getSpeederNum()+1 <= Area.maxSpeeder &&
                                 area.TryUS(Area.maxHunter,Area.maxSpeeder,area.getHunterNum()) == area.getUS()) {
                             area.addSpeeder(player);
                             area.delHunter(player);
                             Start.StartGUI(player,area,1);
                         }
                     }else if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                         if (area.getHunterNum()+1 <= Area.maxHunter) {
                             area.addHunter(player);
                             area.delSpeeder(player);
                             area.updateUS(Area.maxHunter,Area.maxSpeeder);
                             Start.StartGUI(player,area,1);
                         }
                     }else if (event.getCurrentItem().getType() == Material.GLASS) {
                         area.delHunter(player);
                         area.delSpeeder(player);
                         area.updateUS(Area.maxHunter,Area.maxSpeeder);
                         Start.StartGUI(player,area,1);
                     }else if (event.getCurrentItem().getType() == Material.PUMPKIN) {
                         area.changeReady(player);
                         Start.StartGUI(player,area,1);
                         Game.Start(area);
                     }else if (event.getCurrentItem().getType() == Material.BARRIER) {
                         area.delSpeeder(player);
                         area.delHunter(player);
                         area.delPlayer(player);
                         player.kickPlayer(Color.s("&a&lDone"));
                     }
                 }
             }catch (NullPointerException ignored){}
         }
    }
}
