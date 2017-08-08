/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.potionarrow;

import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author TheGuy191919
 */
public class BowShotEvent implements Listener {

    private Plugin plugin;
    private Player player;
    private ItemStack[] playerItems;
    private MaterialData materialData;

    public BowShotEvent(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onBowShot(EntityShootBowEvent Event) {
        //plugin.getServer().broadcastMessage("onBowShot");
        if (Event.getProjectile() instanceof Arrow) {
            //plugin.getServer().broadcastMessage("isArrow");
            if (Event.getEntity() instanceof Player) {
                //plugin.getServer().broadcastMessage("Player detected");
                player = (Player) Event.getEntity();
                materialData = this.playerInvRemove();
            }
            Arrow arrow = (Arrow) Event.getProjectile();
            UUID UUIDofArrow = Event.getProjectile().getUniqueId();
            PotionArrow.HashMapOfArrows.put(UUIDofArrow, 0);
            new ArrowTask(arrow, this.plugin, materialData);
            materialData = null;
        }
    }

    private MaterialData playerInvRemove() {
        int pos = 0;
        if (player.getInventory().contains(Material.POTION)) {
            //plugin.getServer().broadcastMessage(player.getInventory().getContents().toString());
            playerItems = player.getInventory().getContents();
            while (pos <= playerItems.length) {
                if (playerItems[pos] != null) {
                    //plugin.getServer().broadcastMessage(playerItems[pos].toString() + " :" + playerItems[pos].getData().toString());
                    if (playerItems[pos].getData().getItemType() == Material.POTION) {
                        if (!player.getGameMode().name().equalsIgnoreCase("CREATIVE")) {
                            player.getInventory().clear(pos);
                        }
                        return playerItems[pos].getData();
                    }
                }
                pos = pos + 1;
            }
        }
        //meta.getDisplayName()
        return materialData;
    }
}
