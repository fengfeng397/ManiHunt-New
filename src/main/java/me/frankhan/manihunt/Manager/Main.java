package me.frankhan.manihunt.Manager;

import me.frankhan.manihunt.ManiHunt;

public class Main {
    public static String Mode;

    public static void Load() {
        //获取插件运行模式
        Mode = getString("Mode");
        if (Mode.equals("Area")) {
            Area.Load();
            DateBase.Load();
        }else if (Mode.equals("Lobby")) {
            Lobby.Load();
            DateBase.Load();
        }else if (Mode.equals("Both")) {
            Area.Load();
            Lobby.Load();
            DateBase.Load();
        }
    }

    public static void Run() {

    }

    public static String getString(String s) {
        if (ManiHunt.instance.getConfig().contains(s)) {
            return ManiHunt.instance.getConfig().getString(s);
        }else {
            ManiHunt.Logger("&c配置文件中没有名为"+s+"的键![String]");
            return null;
        }
    }
}
