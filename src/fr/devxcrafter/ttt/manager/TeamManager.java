package fr.devxcrafter.ttt.manager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.devxcrafter.ttt.TTT;
import fr.devxcrafter.ttt.gamestats.GameStats;
import fr.devxcrafter.ttt.utils.Data;


public class TeamManager implements Listener {
	
	public static ArrayList<Player> innoscent = new ArrayList<Player>();
	public static ArrayList<Player> detective = new ArrayList<Player>();
	public static ArrayList<Player> terrorist = new ArrayList<Player>();

	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		try {
			if(e.getItem().getType() == Material.NETHER_STAR) {
				if(TTT.gs == GameStats.LOBBY) {
					if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
						Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "§c§lTTT §6§lMenu");
						ItemStack i = new ItemStack(Material.DIAMOND);
						ItemMeta im = i.getItemMeta();
						im.setDisplayName("§9Etre Traître");
						i.setItemMeta(im);
						
						ItemStack i1 = new ItemStack(Material.EMERALD);
						ItemMeta im1 = i1.getItemMeta();
						im1.setDisplayName("§6Etre Détective");
						i1.setItemMeta(im1);
						
						ItemStack i11 = new ItemStack(Material.BARRIER);
						ItemMeta im11 = i11.getItemMeta();
						im11.setDisplayName("§cSortir");
						i11.setItemMeta(im11);
						
						
						inv.setItem(0, i);
						inv.setItem(2, i1);
						inv.setItem(4, i11);
						
						
						p.openInventory(inv);
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
						return;
						
					}
				}
			}
		}catch(Exception e1) {}
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();
			if(e.getInventory().getName().equalsIgnoreCase("§c§lTTT §6§lMenu")) {
				if(TTT.gs == GameStats.LOBBY) {
					if(!TeamManager.detective.contains(p) || !TeamManager.terrorist.contains(p)) { 
					if(e.getCurrentItem().getType() == Material.EMERALD) {
						if(detective.size() < 4) {
						if(StatsManager.detectivepass.get(p.getName()) > 0) {
							StatsManager.detectivepass.put(p.getName(), StatsManager.detectivepass.get(p.getName()) -1);
							TeamManager.detective.add(p);
							p.sendMessage(Data.PREFIX + "§cVous êtes §6§lDétective");
							p.closeInventory();
						} else {
							p.sendMessage(Data.PREFIX + "§4§lErreur §cVous ne pouvez pas prendre le pass §6Détective");
							p.closeInventory();
							p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
							return;
						}
						return;
					} else {
						p.sendMessage("§4§lErreur, §c il y'a trop de Détective !");
					}
					}
						
					if(e.getCurrentItem().getType() == Material.DIAMOND) {
						if(terrorist.size() < 4) {
							if(StatsManager.traitorpass.get(p.getName()) > 0) {
								StatsManager.traitorpass.put(p.getName(), StatsManager.detectivepass.get(p.getName()) -1);
								TeamManager.terrorist.add(p);
								p.sendMessage(Data.PREFIX + "§cVous êtes §6§lTraître");
								p.closeInventory();
							} else {
								p.sendMessage(Data.PREFIX + "§4§lErreur §cVous ne pouvez pas prendre le pass §6Traître");
								p.closeInventory();
								p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
								return;
							}
							return;
						} else {
							p.sendMessage("§4§lErreur, §c il y'a trop de Traître !");
						}
						
						
						return;
					}
					} else {
						p.sendMessage(Data.PREFIX + "§4§lErreur, §c vous avez déjà pris un pass");
						p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
						p.closeInventory();
						return;
					}
					
				} else {
					p.sendMessage(Data.PREFIX + "§cVous ne pouvez plus choisir de pass");
					p.closeInventory();
				}
			}
		}catch(Exception e1) {}
	}
	
	public static void pickRandomTeams(Player p) {
		
	}
	
}
