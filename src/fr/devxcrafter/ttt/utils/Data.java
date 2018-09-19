package fr.devxcrafter.ttt.utils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class Data {
	
	public static final String PREFIX = "§b§l[§4§lTTT§b§l]";
	public static final String NOPERM = PREFIX + "§cVous n'avez pas les permissions necessaires !";
	public static File file = new File("plugins/TTT/spawns.yml");
	public static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
}
