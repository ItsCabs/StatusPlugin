package de.pvphub.statusplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pvphub.statusplugin.manager.OrderManager;
import de.pvphub.statusplugin.utils.OrderStatus;
import de.pvphub.statusplugin.utils.Prefix;

public class ChangeCommand extends Prefix implements CommandExecutor {

	private OrderManager orderManager = new OrderManager();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (player.hasPermission("statusplugin.set")) {
				if (args.length == 3) {
					Integer orderNumber = Integer.valueOf(args[1]);
					if (orderManager.orderExists(orderNumber)) {
						if (args[0].equalsIgnoreCase("status")) {
							if (args[2].equalsIgnoreCase("STARTED")) {
								orderManager.setStatus(orderNumber, OrderStatus.STARTED);

							} else if (args[2].equalsIgnoreCase("FINISHED")) {
								orderManager.setStatus(orderNumber, OrderStatus.FINISHED);

							} else if (args[2].equalsIgnoreCase("NEARLYFINISHED")) {
								orderManager.setStatus(orderNumber, OrderStatus.NEARLY_FINISHED);

							} else if (args[2].equalsIgnoreCase("INQUEUE")) {
								orderManager.setStatus(orderNumber, OrderStatus.INQUEUE);

							}

							player.sendMessage(PREFIX + "Der Status von der Auftragsnummer§7: §e" + orderNumber + " §7wurde erfolgreich auf§7: §e" + args[2] + " §7geändert§7!");

						} else if (args[0].equalsIgnoreCase("passwort")) {
							orderManager.setPassword(orderNumber, args[2]);
							
							player.sendMessage(PREFIX + "Das Passwort von der Auftragsnummer§7: §e" + orderNumber + " §7wurde erfolgreich auf§7: §e" + args[2] + " §7geändert!");

						} else if (args[0].equalsIgnoreCase("prozent")) {
							orderManager.setPercent(orderNumber, Integer.valueOf(args[2]));

							player.sendMessage(PREFIX + "Das Passwort von der Auftragsnummer§7: §e" + orderNumber + " §7wurde erfolgreich auf§7: §e" + args[2] + " §7geändert!");
							
						}

					}

				} else {

				}

			}

		}

		return false;
	}

}
