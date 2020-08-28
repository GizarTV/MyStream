package MyStream.main;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MyStream extends JavaPlugin{

	public void onEnable(){
		
		File config = new File(getDataFolder() + File.separator + "config.yml");
		if(!config.exists()){
		    getConfig().options().copyDefaults(true);
		    saveDefaultConfig();
		    
		}
		Bukkit.getPluginManager().registerEvents(new Handler(this), this);
		
		getCommand("Stream").setExecutor(new Commands(this));
	}
	
	ArrayList<String> streams = new ArrayList<String>();
}
