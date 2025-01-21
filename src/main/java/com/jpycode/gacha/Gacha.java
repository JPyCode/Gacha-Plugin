package com.jpycode.gacha;

import com.jpycode.gacha.commands.GachaCommand;
import com.jpycode.gacha.utils.GachaGUI;
import org.bukkit.plugin.java.JavaPlugin;

public final class Gacha extends JavaPlugin {


    @Override
    public void onEnable() {

        //Listeners and GUI
        getServer().getPluginManager().registerEvents(new GachaGUI(null), this);

        //Commands
        getCommand("gacha").setExecutor(new GachaCommand(this));

        getLogger().info("@ Gacha Plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("@ Gacha Plugin disabled.");
    }
}
