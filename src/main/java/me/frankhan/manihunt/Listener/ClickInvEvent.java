package me.frankhan.manihunt.Listener;

import me.frankhan.manihunt.Class.Area;
import me.frankhan.manihunt.Gui.Start;
import me.frankhan.manihunt.Manager.Game;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ClickInvEvent implements Listener {
    @EventHandler
    public void ClickInvEvent(InventoryClickEvent event) {
         Player player = (Player) event.getWhoClicked();
         World w = player.getWorld();
         if (event.getView().getTitle().startsWith(Color.s("&e&l准备开始!"))) {
             event.setCancelled(true);
             try{
                 Area area = null;
                 for (Area a: me.frankhan.manihunt.Manager.Area.Areas) {
                     if (a.getWorld().contains(w)) {
                         area = a;
                     }
                 }
                 if (area != null){
                     if (event.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                         if (area.getChecker() > area.getSpeeder().size() &&
                                 area.getSpeederNum()+1 <= me.frankhan.manihunt.Manager.Area.maxSpeeder) {
                             area.addSpeeder(player);
                             area.addWaiter(player);
                             area.delHunter(player);
                             Start.StartGUINow(player,area);
                         }
                     }else if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
                         if (area.getHunterNum()+1 <= me.frankhan.manihunt.Manager.Area.maxHunter) {
                             area.addHunter(player);
                             area.addWaiter(player);
                             area.delSpeeder(player);
                             me.frankhan.manihunt.Manager.Area.updateChecker(area);
                             Start.StartGUINow(player,area);
                         }
                     }else if (event.getCurrentItem().getType() == Material.GLASS) {
                         area.delHunter(player);
                         area.delSpeeder(player);
                         area.delWaiter(player);
                         me.frankhan.manihunt.Manager.Area.updateChecker(area);
                         Start.StartGUINow(player,area);
                     }else if (event.getCurrentItem().getType() == Material.PUMPKIN) {
                         area.changeReady(player);
                         Start.StartGUINow(player,area);
                         Game.CheckFine(area);
                     }else if (event.getCurrentItem().getType() == Material.BARRIER) {
                         area.delSpeeder(player);
                         area.delHunter(player);
                         area.delWaiter(player);
                         area.delPlayer(player);
                         player.kickPlayer(Color.s("&a&lDone"));
                     }else if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                         ItemStack i = event.getCurrentItem();
                         InteractEvent.compass.put(player,player.getServer().getPlayer(
                                 Objects.requireNonNull(i.getItemMeta()).getDisplayName()));
                     }
                 }
             }catch (NullPointerException ignored){}
         }
    }
}
