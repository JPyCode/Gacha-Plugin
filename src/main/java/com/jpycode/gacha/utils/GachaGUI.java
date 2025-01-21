package com.jpycode.gacha.utils;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.*;

public class GachaGUI implements Listener {
    private final Player p;
    private final Inventory inventory;
    private final Plugin plugin;
    private final Map<ItemStack, Double> rewards = new HashMap<>();
    private final List<ItemStack> displayItems = new ArrayList<>();
    private BukkitRunnable currentTask;


    public GachaGUI(Player player) {
        this.p = player;
        this.plugin = Bukkit.getPluginManager().getPlugin("Gacha");
        this.inventory = Bukkit.createInventory(null, 3 * 9, "Gacha GUI");


        // Better with a configuration file
        rewards.put(new ItemStack(Material.DIAMOND_BLOCK, 1), 0.05);
        rewards.put(new ItemStack(Material.IRON_BLOCK, 1), 0.1);
        rewards.put(new ItemStack(Material.COAL_BLOCK, 1), 0.35);
        rewards.put(new ItemStack(Material.REDSTONE_BLOCK, 1), 0.1);
        rewards.put(new ItemStack(Material.GOLD_BLOCK, 1), 0.2);
        rewards.put(new ItemStack(Material.LAPIS_BLOCK, 1), 0.2);


        displayItems.addAll(rewards.keySet());
    }



    public void open() {
        p.openInventory(inventory);
        startAnimation();
    }

    private void startAnimation() {
        Random random = new Random();
        ItemStack reward = getRandomReward();
        int midSlot = 13;
        int[] middleSlots = {9, 10, 11, 12, 13, 14, 15, 16, 17};
        int delay = 3;
        List<ItemStack> currentItems = new ArrayList<>();
        for (int i = 0; i < middleSlots.length; i++) {
            currentItems.add(displayItems.get(random.nextInt(displayItems.size())));
        }
        currentTask = new BukkitRunnable() {
            int ticks = 0;
            int maxTicks = 40;


            @Override
            public void run() {
                currentItems.add(0, displayItems.get(random.nextInt(displayItems.size())));
                currentItems.remove(currentItems.size() - 1);

                // Updates inventory slots
                for (int i = 0; i < middleSlots.length; i++) {
                    inventory.setItem(middleSlots[i], currentItems.get(i));
                }

                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 0.3f, 1);

                if(ticks >= maxTicks) {
                    ItemStack rewardGotcha = inventory.getItem(13);
                    for(int slot : middleSlots) {
                        inventory.setItem(slot, null);
                    }


                    p.sendMessage(ChatColor.GREEN + "Você ganhou: "
                            + ChatColor.AQUA + rewardGotcha.getAmount()
                            + "x " + rewardGotcha.getType().name());
                    p.getInventory().addItem(rewardGotcha);


                    inventory.setItem(midSlot, rewardGotcha);

                    p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK_ROCKET);
                    cancel();
                }
                ticks++;
            }
        };

        currentTask.runTaskTimer(plugin, 2, delay);
    }

    @EventHandler
    public void onGachaClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("gacha gui")) {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();
            if(currentTask != null && !currentTask.isCancelled()) currentTask.cancel();
        }
    }

    private ItemStack getRandomReward() {
        double roll = new Random().nextDouble();
        double chance = 0.0;
        for (Map.Entry<ItemStack, Double> entry : rewards.entrySet()) {
            chance += entry.getValue();
            if (roll <= chance) {
                return entry.getKey();
            }
        }
        // If there is an error and no reward is set.
        return null;
    }
}
