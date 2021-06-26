package me.frankhan.manihunt.Tools;

import org.bukkit.ChatColor;

import java.util.List;

public class Color {
    public static String s(String s) {
        return ChatColor.translateAlternateColorCodes('&',s);
    }
    public static List<String> sl(List<String> ls) {
        for (int i = 0;i < ls.size();i++) {
            ls.set(i,s(ls.get(i)));
        }
        return ls;
    }
}
