package fr.devxcrafter.ttt.manager;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.devxcrafter.ttt.utils.Data;


public class SpawnManager implements CommandExecutor  {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setlobby")) {
			Player p = (Player) sender;
			if(p.hasPermission("ttt.setlobby")) {
				if(args.length == 1) {
				Location loc = p.getLocation();
				double x = loc.getX();
				double y = loc.getY();
				double z = loc.getZ();
				double yaw = loc.getYaw();
				double pitch = loc.getPitch();
				String Worldname = loc.getWorld().getName();
			
				Data.config.set("Spawn" + args[0] + ".X", x);
				Data.config.set("Spawn" + args[0] + ".Y", y);
				Data.config.set("Spawn" + args[0] + ".Z", z);
				Data.config.set("Spawn" + args[0] + ".XYaw", yaw);
				Data.config.set("Spawn" + args[0] + ".Pitch", pitch);
				Data.config.set("Spawn" + args[0] + ".Worldname", Worldname);
				try {
					Data.config.save(Data.file);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				} else {
					p.sendMessage(Data.PREFIX + "Usage de la commande : /setlobby [Nombre]");
				}
				
			} else {
				p.sendMessage(Data.NOPERM);
			}
		}
		
		return false;
	}
	
public static void teleportToSpawn(Player p, int Spawnnb) {
		
		int x = Data.config.getInt("Spawn" + Spawnnb + ".X");
		int y = Data.config.getInt("Spawn" + Spawnnb + ".Y");
		int z = Data.config.getInt("Spawn" + Spawnnb + ".Z");
		int yaw = Data.config.getInt("Spawn" + Spawnnb + ".Yaw");
		int pitch = Data.config.getInt("Spawn" + Spawnnb + ".Pitch");
		String Worldname = Data.config.getString("Spawn" + Spawnnb + ".Worldname");
		Location loc = new Location(Bukkit.getWorld(Worldname), x, y, z);
		
		p.teleport(loc);
		
	}

}
