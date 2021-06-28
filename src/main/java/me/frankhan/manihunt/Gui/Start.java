package me.frankhan.manihunt.Gui;

import me.frankhan.manihunt.Class.area;
import me.frankhan.manihunt.Runnable.Gui;
import me.frankhan.manihunt.Tools.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Start {
    public static void StartGUI(Player player, area area, int mode) {
        Inventory gui = Bukkit.createInventory(player,27, Color.s("&6&l准备开始!当前解锁的速通者个数:"+(int)area.getUS()));


        //速通者物品
        ItemStack speeder = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta speederM = speeder.getItemMeta();
        //设置名字
        speederM.setDisplayName(Color.s("&a&l速通者"));
        //设置Lore
        List<String> speederl = Color.sl(new ArrayList<>(Arrays.asList("&e当前&b&l"+area.getSpeederNum()+"&e人",
                "&e名单:"+area.getSpeederS())));
        if (area.speederContains(player))speederl.add("&e&l你已经选择了速通者阵营");
        speederM.setLore(speederl);
        //设置Meta并设置位置
        speeder.setItemMeta(speederM);
        gui.setItem(11,speeder);


        //猎人物品
        ItemStack hunter = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta hunterM = hunter.getItemMeta();
        //设置名字
        hunterM.setDisplayName(Color.s("&c&l猎 人"));
        //设置Lore
        List<String> hunterl = Color.sl(new ArrayList<>(Arrays.asList("&e当前&b&l"+area.getHunterNum()+"&e人",
                "&e名单:"+area.getHunterS())));
        if (area.hunterContains(player))hunterl.add("&e&l你已经选择了猎人阵营");
        hunterM.setLore(hunterl);
        hunter.setItemMeta(hunterM);
        gui.setItem(13,hunter);


        //旁观者物品
        ItemStack spector = new ItemStack(Material.GLASS);
        ItemMeta spectorM = spector.getItemMeta();
        //设置名字
        spectorM.setDisplayName(Color.s("&f&l旁观者"));
        //设置Lore
        List<String> spectorl = new ArrayList<>();
        if (!area.hunterContains(player)&&!area.speederContains(player))  spectorl.add(Color.s("&e&l你已经选择了旁观者阵营"));
        spectorM.setLore(spectorl);
        spector.setItemMeta(spectorM);
        gui.setItem(15,spector);


        //准备按钮
        ItemStack ready = new ItemStack(Material.PUMPKIN);
        ItemMeta readyM = ready.getItemMeta();
        //设置名字+Lore
        List<String> readyl;
        if (area.speederContains(player) || area.hunterContains(player)) {
            if (area.getReady(player)) {readyM.setDisplayName(Color.s("&a&l已准备"));
            }else {readyM.setDisplayName(Color.s("&c&l未准备"));}
            int total = area.getSpeederNum() + area.getHunterNum();
            readyl = new ArrayList<>(Arrays.asList(Color.s("&e准备人数:&b&l"+area.getReadyNum()+"&e/&b&l"+total),
                    Color.s("&e名单:"+area.getReadyS())));
        }else {
            int total = area.getSpeederNum() + area.getHunterNum();
            readyl = new ArrayList<>(Arrays.asList(Color.s("&e准备人数:&b&l"+area.getReadyNum()+"&e/&b&l"+total),
                    Color.s("&e名单:"+area.getReadyS()),
                    Color.s("&c旁观者无法准备，请等待其他人准备！")));
        }
        readyM.setLore(readyl);
        ready.setItemMeta(readyM);
        gui.setItem(22,ready);


        //离开按钮
        ItemStack leave = new ItemStack(Material.BARRIER);
        ItemMeta leaveM = leave.getItemMeta();
        //设置名字
        leaveM.setDisplayName(Color.s("&c&l离开"));
        leave.setItemMeta(leaveM);
        gui.setItem(26,leave);

        if (mode == 0) {
            Gui.tasks.put(gui,3);
            Gui.dic.put(gui,player);
        }else {
            //打开GUI
            player.openInventory(gui);
            //更新GUI
            for (Player p : area.getPlayers()) {
                player.openInventory(gui);
            }
        }

    }
}
