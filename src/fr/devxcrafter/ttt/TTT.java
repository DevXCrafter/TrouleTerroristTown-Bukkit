package fr.devxcrafter.ttt;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.devxcrafter.ttt.games.JoinManager;
import fr.devxcrafter.ttt.games.MainListener;
import fr.devxcrafter.ttt.games.QuitMessage;
import fr.devxcrafter.ttt.gamestats.GameStats;
import fr.devxcrafter.ttt.manager.SpawnManager;
import fr.devxcrafter.ttt.manager.TeamManager;
import fr.devxcrafter.ttt.utils.Data;

public class TTT extends JavaPlugin {
	
	public static TTT instance;
	public static GameStats gs;
	public static int cd;
	public static int waitforplayer;
	
	@SuppressWarnings("deprecation")
	@Override	
	public void onEnable() {
		waitforplayer = 10;
		gs = GameStats.LOBBY;
		Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "§7TT viens de démarrer");
		loadEvents();
		loadCommands();
		cd = Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				if(Bukkit.getOnlinePlayers().size() < 2) {
					if(TTT.gs == GameStats.LOBBY) {
						if(waitforplayer == 0) {
						Bukkit.broadcastMessage(Data.PREFIX + "§cEn Attente de joueur...");
						waitforplayer = 10;
						}
					} else {
						Bukkit.getScheduler().cancelTask(cd);
					}
				}
				
			}
		}, 20, 20);
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(Data.PREFIX + "§7TT viens de s'éteindre");
	}

	
	public static TTT getInstance() {
		return instance;
	}
	
	public void loadEvents() {
		Bukkit.getPluginManager().registerEvents(new JoinManager(), this);
		Bukkit.getPluginManager().registerEvents(new QuitMessage(), this);
		Bukkit.getPluginManager().registerEvents(new MainListener(this), this);
		Bukkit.getPluginManager().registerEvents(new TeamManager(), this);
		
	}
	
	public void loadCommands() {
		getCommand("setlobby").setExecutor(new SpawnManager());
	}

}
