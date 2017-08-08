
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.theguy191919.potionarrow;

import java.util.List;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 *
 * @author TheGuy191919
 */
public class ArrowTask extends BukkitRunnable {

    private Plugin plugin;
    private Arrow arrow;
    private final Vector NoSpeed = new Vector();
    private MaterialData materialData;

    public ArrowTask(Arrow arrow, Plugin plugin, MaterialData materialData) {
        this.plugin = plugin;
        this.arrow = arrow;
        this.runTaskTimer(plugin, 1L, 1L);
        this.materialData = materialData;
        //this.plugin.getServer().broadcastMessage("PotionSplashStart");
    }

    @Override
    public void run() {
        if (this.arrow.isOnGround() || this.arrow.isDead()) {

            Location location = this.arrow.getLocation();
            if (materialData != null) {
                ItemStack itemstack = new ItemStack(Material.POTION);
                itemstack = materialData.toItemStack();

                ThrownPotion thrownPotion = this.arrow.getWorld().spawn(location, ThrownPotion.class);
                thrownPotion.setItem(itemstack);
            }

            PotionArrow.HashMapOfArrows.remove(this.arrow.getUniqueId());
            cancel();
            return;
        }

        List<Entity> NearestEntity = this.arrow.getNearbyEntities(4d, 4d, 4d);
        if (!NearestEntity.isEmpty() && NearestEntity.get(0) instanceof Player) {
            Player NearPlayer = (Player) NearestEntity.get(0);
            if (NearPlayer.isBlocking()) {
                this.arrow.setVelocity(NoSpeed);
                //plugin.getServer().broadcastMessage("Get Blocked");
            }
            //double DistanceOfEntity = this.arrow.getLocation().distance(NearestEntity.get(0).getLocation());
        }
        //plugin.getServer().broadcastMessage("PotionSplashRun");
        this.arrow.getWorld().playEffect(this.arrow.getLocation(), Effect.SMOKE, 0);
        //plugin.getServer().broadcastMessage("Shots Fired");
    }
//    private void potionCreate(){
//                    Location location = this.arrow.getLocation();
//            // Create a potion type
//            Potion potion = new Potion(PotionType.INSTANT_DAMAGE);
//
//            // Make it a splash potion
//            potion.setSplash(true);
//
//            //Make itemstack of potions
//            ItemStack itemstack = new ItemStack(Material.POTION);
//            potion.apply(itemstack);
//            // Spawn the potion
//            ThrownPotion thrownPotion = this.arrow.getWorld().spawn(location, ThrownPotion.class);
//            thrownPotion.setItem(itemstack);
//            //Potion potion = (Potion)location.getWorld().spawnEntity(location, EntityType.SPLASH_POTION);
//            //this.arrow.getWorld().spawnEntity(this.arrow.getLocation(), EntityType.SPLASH_POTION);
//            ArrowShield.HashMapOfArrows.remove(this.arrow.getUniqueId());
//            //Potion potion = new Potion();
//    }
}
