package com.jpycode.gacha.commands;

import com.jpycode.gacha.utils.GachaGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GachaCommand implements CommandExecutor {
    private final Plugin plugin;

    public GachaCommand(Plugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player p) {
            new GachaGUI(p).open();
            return true;


        } else {
            commandSender.sendMessage("Comando disponível apenas para jogadores.");
            return true;
        }

    }
}
