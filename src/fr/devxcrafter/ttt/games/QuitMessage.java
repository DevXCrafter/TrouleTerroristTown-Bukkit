package fr.devxcrafter.ttt.games;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.devxcrafter.ttt.TTT;
import fr.devxcrafter.ttt.gamestats.GameStats;
import fr.devxcrafter.ttt.utils.Data;

public class QuitMessage implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(TTT.gs == GameStats.LOBBY) {
			e.setQuitMessage(Data.PREFIX + "§c" + p.getName() + "§eviens de quitté la partie (§a" + Bukkit.getOnlinePlayers() +  "§c/§a" + Bukkit.getServer().getMaxPlayers());
			return;
			
		}
	}

}
