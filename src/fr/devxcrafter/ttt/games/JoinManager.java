package fr.devxcrafter.ttt.games;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.devxcrafter.ttt.TTT;
import fr.devxcrafter.ttt.gamestats.GameStats;
import fr.devxcrafter.ttt.manager.Inventorys;
import fr.devxcrafter.ttt.manager.Scoreboard;
import fr.devxcrafter.ttt.utils.Data;

public class JoinManager implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(TTT.gs == GameStats.LOBBY) {
			e.setJoinMessage(Data.PREFIX + "§c" + p.getName() + "§eviens de rejoindre la partie (§a" + Bukkit.getOnlinePlayers() +  "§c/§a" + Bukkit.getServer().getMaxPlayers());
		} else {
			e.setJoinMessage(null);
		}
		Scoreboard.setScorebaord(p); 
		Inventorys.getLobbyItems(p);
		
	}

}
