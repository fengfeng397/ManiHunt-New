package me.frankhan.manihunt.Gui;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.frankhan.manihunt.Class.Area;
import me.frankhan.manihunt.ManiHunt;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Compass {
    public static void Compass(Area a, Player p) {
        int v = Integer.parseInt(ManiHunt.version);
        Inventory gui = Bukkit.createInventory(p,54, Color.s("&a&l速通者名单"));
        int i = 0;
        for (Player player:a.getSpeeder()) {
            ItemStack head;
            if (v >= 13) {
                head = new ItemStack(Material.PLAYER_HEAD);
            }else {
                head = new ItemStack(Material.STONE);
            }
            NBTItem ni = new NBTItem(head);
            NBTCompound dis = ni.addCompound("display");
            dis.setString("Name",player.getDisplayName());
            NBTCompound so = ni.addCompound("SkullOwner");
            so.setString("Name", player.getCustomName());
            head = ni.getItem();
            gui.setItem(i,head);
            i++;
        }
        p.openInventory(gui);
    }
}
