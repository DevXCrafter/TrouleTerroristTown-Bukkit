package fr.devxcrafter.ttt.games;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.devxcrafter.ttt.TTT;
import fr.devxcrafter.ttt.gamestats.GameStats;
import fr.devxcrafter.ttt.manager.TeamManager;
import fr.devxcrafter.ttt.utils.Data;

public class MainListener implements Listener {
	
	public MainListener(fr.devxcrafter.ttt.TTT TTT) {
		this.pl = TTT;
	}
	private fr.devxcrafter.ttt.TTT pl;
	
	static int cd;
	static int cdc;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(TTT.gs == GameStats.LOBBY) {
			Player p = e.getPlayer();
			e.setJoinMessage(Data.PREFIX + "§c" + p.getName() + "§eviens de rejoindre la partie (§a" + Bukkit.getOnlinePlayers() +  "§c/§a" + Bukkit.getServer().getMaxPlayers());
			
			if(Bukkit.getOnlinePlayers().size() < 2) {
				cdc = 61;
				Bukkit.broadcastMessage(Data.PREFIX + "§7Il y'a suffisament de joueur connecté ! Le jeu peut commencer !");
				
				cd = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
					
					@Override
					public void run() {
						cdc--;
						switch(cdc) {
						case 60:
							Bukkit.broadcastMessage(Data.PREFIX + "§7Le jeu commence dans §6" + cdc + " secondes !");
							
							break;
							
						case 50:
							Bukkit.broadcastMessage(Data.PREFIX + "§7Le jeu commence dans §6" + cdc + " secondes !");
							
							break;
							
						case 40:
							Bukkit.broadcastMessage(Data.PREFIX + "§7Le jeu commence dans §6" + cdc + " secondes !");
							
							break;
							
						case 30:
							Bukkit.broadcastMessage(Data.PREFIX + "§7Le jeu commence dans §6" + cdc + " secondes !");
							
							break;
							
						case 20:
							Bukkit.broadcastMessage(Data.PREFIX + "§7Le jeu commence dans §6" + cdc + " secondes !");
							
							break;
							
						case 10:
							Bukkit.broadcastMessage(Data.PREFIX + "§7Le jeu commence dans §6" + cdc + " secondes !");
							
							break;
							
						case 1:
							Bukkit.broadcastMessage(Data.PREFIX + "§7Le jeu commence dans §6" + cdc + " seconde !");
							
							Bukkit.getScheduler().cancelTask(cd);
							
							TTT.gs = GameStats.INGAME;
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(!TeamManager.detective.contains(all) || !TeamManager.terrorist.contains(all)) {
									TeamManager.pickRandomTeams(all);
								}
							}
							
							break;

						}
						
						if(cdc < 5 && cdc > 1) {
							Bukkit.broadcastMessage(Data.PREFIX + "§7Le jeu commence dans §6" + cdc + " secondes !");
						}
						
						

						
					}
				}, 20, 20);
			}
		}
		
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(Bukkit.getOnlinePlayers().size() == 2) {
			if(TTT.gs == GameStats.LOBBY) {
				if(Bukkit.getScheduler().isCurrentlyRunning(cd)) {
					Bukkit.getScheduler().cancelTask(cd);
					Bukkit.broadcastMessage(Data.PREFIX + "§7Le compte à rebours a été stoppé, car il n'y a pas assez de joueurs !");
					cdc = 61;
				}
			}
		}
	}

}
