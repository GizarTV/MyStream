package MyStream.main;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{
	
	private MyStream plugin;

	public Commands(MyStream instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player)sender;
		
		if(!sender.hasPermission("mystream.use")) {
			sender.sendMessage(plugin.getConfig().getString("messages.noper").replace("&", "§"));
			return true;
		}
		
		if(!sender.hasPermission("mystream.stop")) {
			sender.sendMessage(plugin.getConfig().getString("messages.noper").replace("&", "§"));
			return true;
		}
		
		if(args.length == 0) {
			 if(sender.hasPermission("mystream.use")) {
				 UUID player1 = player.getUniqueId();
				 String playername = plugin.getConfig().getString("youtubers." + player1 + ".player");
				 if(sender.getName() == playername) {
				     for(Player p : Bukkit.getOnlinePlayers()) {
				    	  String name = plugin.getConfig().getString("youtubers." + player1 + ".name");
				    	  String url = plugin.getConfig().getString("youtubers." + player1 + ".url");
				    	  p.sendMessage(plugin.getConfig().getString("messages.messages").replace("%name%", name).replace("%url%", url).replace("&", "§"));
				    	  if(!plugin.streams.contains(sender.getName())) {
					    		 plugin.streams.add(sender.getName());
		                         return true;
				    	  }
				    }   
				}else {
				  return false;
				}
			}
		}
		
        if(args.length == 1) {
        	if(args[0].equalsIgnoreCase("stop")) {
        		if(sender.hasPermission("mystream.stop")) {
        			if(plugin.streams.contains(sender.getName())) {
   	    		        plugin.streams.remove(sender.getName());
        			}
        		    for(Player p : Bukkit.getOnlinePlayers()) {
        	    		p.sendMessage(plugin.getConfig().getString("messages.stopstream").replace("&", "§").replace("%name%", sender.getName()));
        	    		     return true;
        		        }
        		  }else {
        			  sender.sendMessage(plugin.getConfig().getString("nostream").replace("&", "§"));
        			  return true;
        		}
        	  }
        	 return true;
        	}
            if(args.length == 1) {
        	   if(args[0].equalsIgnoreCase("reload")) {
          		   if(sender.hasPermission("mystream.reload")) {
          			   sender.sendMessage("§aReloded starting!");
          			   plugin.reloadConfig();
          			   sender.sendMessage("§aReloded complited!");
          		       return true;
          	}
           }else {
        	   return false;
              }
            }
		
		if(args.length == 2) {
			if(sender.hasPermission("mystream.use")) {
			    UUID player1 = player.getUniqueId();
			    String playername = plugin.getConfig().getString("youtubers." + player1 + ".player");
			    if(sender.getName() == playername) {
			    	for(Player p : Bukkit.getOnlinePlayers()) {
			    		 String name = plugin.getConfig().getString("youtubers." + player1 + ".name");
			    		 String url = plugin.getConfig().getString("youtubers." + player1 + ".url");
			    		 p.sendMessage(plugin.getConfig().getString("messages.messages").replace("%name%", name).replace("%url%", url).replace("&", "§"));
			    		 if(!plugin.streams.contains(sender.getName())) {
				    		 plugin.streams.add(sender.getName());
			    		 }
			    		}
			    }else {
			        String name = args[0].replace("/", " ").replace("!", " ").replace("@", " ").replace("#", " ").replace("$", " ").replace("%", " ").replace("^", " ").replace("*", " ").replace("+", " ");
			        String url = args[1];
			        plugin.getConfig().set("youtubers." + player.getUniqueId() + ".player", sender.getName());
			        plugin.getConfig().set("youtubers." + player.getUniqueId() + ".name", name);
			        plugin.getConfig().set("youtubers." + player.getUniqueId() + ".url", url);
			        plugin.saveConfig();
			        sender.sendMessage(plugin.getConfig().getString("messages.successfully").replace("&", "§"));
			  }
		   }
			return true;
		}
		
		return true;
	}

}
