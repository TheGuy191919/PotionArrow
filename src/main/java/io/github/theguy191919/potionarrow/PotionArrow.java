/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.potionarrow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;

/**
 *
 * @author TheGuy191919
 */
public class PotionArrow extends JavaPlugin {

    public List<Entity> ListOfEntities;
    public static Map<UUID, Integer> HashMapOfArrows = new HashMap<>();
    public Potion[] listOfPotions;

    @Override
    public void onEnable() {
        getLogger().info("Potion Arrow is enabled");
        new BowShotEvent(this);
        //this.reloadEntities();
        //getLogger().info("Entities and arrows Loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Potion Arrow had just been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        getLogger().info("GotCmmand");
//        getLogger().info("Number of Args: " + args.length);
//        getLogger().info(args.toString());
//        int pos = 0;
//        while (pos < args.length) {
//            getLogger().info(args[pos]);
//            pos = pos + 1;
//        }

        if (cmd.getName().equalsIgnoreCase("PotionArrow") && sender.hasPermission("PotionArrow")) {
            //this.getServer().broadcastMessage("Name is enabled");
            //fix out of range exception when doing /potionarrow
            if (args.length < 1) {
                return false;
            }
            if (args[0].equalsIgnoreCase("Disable") && sender.hasPermission("PotionArrow.Disable")) {
                sender.sendMessage("Disabling");
                this.getPluginLoader().disablePlugin(this);
                return true;
            }
            if (args[0].equalsIgnoreCase("help") && sender.hasPermission("PotionArrow.Help")) {
                sender.sendMessage("--Help--\nTo disable this plugin, do \"/potionarrow disable\"\nTo know more about this plugin, do \"/potionarrow about\"\nTo start using this plugin, log on, put potions of any kind in your inventory, ans shot your bow at someone.");
                return true;
            }
            if (args[0].equalsIgnoreCase("about") && sender.hasPermission("PotionArrow.About")) {
                sender.sendMessage("This plugin is made by TheGuy191919.\nThis is version 1.0\nThank you for using this plugin\nWebsite: TheGuy191919.github.io\nPMs are welcomed");
                return true;
            }
        }
        return false;
    }
//    public void reloadEntities() {
//        HashMapOfArrows.clear();
//        getLogger().info("ReloadingEntities");
//        ListOfEntities = Bukkit.getServer().getWorld("world").getEntities();
//        int NumberOfEntities = ListOfEntities.size();
//        getLogger().info(NumberOfEntities + "");
//        int CurrentListPos = 1;
//        while (CurrentListPos < NumberOfEntities) {
//            String message = "Item is: " + ListOfEntities.get(CurrentListPos).getType().name() + " at " + CurrentListPos;
//            getLogger().info(message);
//            if (ListOfEntities.get(CurrentListPos).getType().name().equalsIgnoreCase("arrow")) {
//                HashMapOfArrows.put(ListOfEntities.get(CurrentListPos).getUniqueId(), 1);
//                getLogger().info("added an arrow");
//            }
//            CurrentListPos = CurrentListPos + 1;
//        }
//        getLogger().info("Arrows loaded: " + HashMapOfArrows.size());
//    }
}
