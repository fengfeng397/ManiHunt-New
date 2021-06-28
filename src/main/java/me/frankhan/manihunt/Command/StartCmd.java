package me.frankhan.manihunt.Command;

import me.frankhan.manihunt.Class.area;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            area a = me.frankhan.manihunt.Manager.Area.fromWhichArea(p);
            if (a != null && !a.getState().equals("P") && !a.getState().equals("R")) {
                me.frankhan.manihunt.Gui.Start.StartGUI(p,a,1);
            }
        }
        return false;
    }
}
