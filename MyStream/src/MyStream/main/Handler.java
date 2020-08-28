package MyStream.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Handler implements Listener{
	
	private MyStream plugin;

	public Handler(MyStream plugin) {
	   this.plugin = plugin;
	}
	
	@EventHandler
	public void onStopStream(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if(plugin.streams.contains(player.getName())) {
		   plugin.streams.remove(player.getName());
		}
		for(Player p : Bukkit.getOnlinePlayers()) {
    		    p.sendMessage(plugin.getConfig().getString("messages.stopstream").replace("&", "§").replace("%name%", e.getPlayer().getName()));
    		}
	    }
	  
}


