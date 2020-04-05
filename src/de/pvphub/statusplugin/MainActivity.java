package de.pvphub.statusplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.pvphub.statusplugin.commands.AddOrderCommand;
import de.pvphub.statusplugin.commands.ChangeCommand;
import de.pvphub.statusplugin.commands.StatusCommand;
import de.pvphub.statusplugin.listener.PlayerChatListener;
import de.pvphub.statusplugin.manager.OrderManager;

public class MainActivity extends JavaPlugin {

	public void onEnable() {
		System.out.println("[StatusPlugin] started successfully!");
		
		OrderManager orderManager = new OrderManager();
		orderManager.createFile();
		
		addCommands();
		registerListener();
		
	}

	private void registerListener() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new PlayerChatListener(this), this);
		
	}

	private void addCommands() {
		getCommand("change").setExecutor(new ChangeCommand());
		getCommand("status").setExecutor(new StatusCommand());
		getCommand("addorder").setExecutor(new AddOrderCommand());
		
	}
	
}
