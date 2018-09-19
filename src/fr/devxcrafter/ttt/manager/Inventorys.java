package fr.devxcrafter.ttt.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Inventorys {
	
	public static void getLobbyItems(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setItem(0, ItemCreator.createItem(Material.PAPER, 1, 0, "§6§lPass Menu", "§9§lChoisissez votre rôle"));
		p.getInventory().setItem(8, ItemCreator.createItem(Material.DARK_OAK_DOOR, 1, 0, "§4§lQuitter", "§7§lQuitter la partie"));
		p.updateInventory();
	}
	

}
