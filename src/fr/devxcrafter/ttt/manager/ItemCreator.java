package fr.devxcrafter.ttt.manager;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCreator {
	
	public static ItemStack createItem(Material m, int nb, int SubID, String Displayname, String lore) {

	ItemStack i = new ItemStack(m, nb, (short) SubID);
	ItemMeta im = i.getItemMeta();
	im.setDisplayName(Displayname);
	ArrayList<String> list = new ArrayList<>();
	list.add(lore);
	im.setLore(list);
	i.setItemMeta(im);
	return i;
	
	}
}
