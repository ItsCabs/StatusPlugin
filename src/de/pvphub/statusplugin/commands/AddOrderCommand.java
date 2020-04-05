package de.pvphub.statusplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pvphub.statusplugin.manager.OrderManager;
import de.pvphub.statusplugin.utils.Prefix;

public class AddOrderCommand extends Prefix implements CommandExecutor {

	private OrderManager orderManager = new OrderManager();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (player.hasPermission("statusplugin.add")) {
				String name = args[0];
				orderManager.addOrder(name);

				player.sendMessage(PREFIX + "Der Auftrag" + args[0] + " wurde erfolgreich erstellt!");

			}

		}

		return false;
	}

}
