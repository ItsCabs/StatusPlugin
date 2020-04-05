package de.pvphub.statusplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pvphub.statusplugin.utils.Data;
import de.pvphub.statusplugin.utils.Prefix;

public class StatusCommand extends Data implements CommandExecutor {

	private Prefix prefix = new Prefix();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	
		if(sender instanceof Player) {
			Player player = (Player) sender;
			inProcess.put(player, player.getName());
			
			player.sendMessage(prefix.PREFIX + "Schreibe sie bitte ihre gültige Auftragsnummer,");
			player.sendMessage(prefix.PREFIX + "und \"cancel\" um den Vorgang abzubrechen.");
			
		}
		
		return false;
	}

}
