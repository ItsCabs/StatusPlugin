package de.pvphub.statusplugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import de.pvphub.statusplugin.MainActivity;
import de.pvphub.statusplugin.manager.OrderManager;
import de.pvphub.statusplugin.utils.Data;
import de.pvphub.statusplugin.utils.Prefix;

@SuppressWarnings("deprecation")
public class PlayerChatListener extends Data implements Listener {

	private Prefix prefix = new Prefix();
	private OrderManager orderManager = new OrderManager();

	private MainActivity plugin;

	public PlayerChatListener(MainActivity mainActivity) {
		this.plugin = mainActivity;

	}

	private static Integer orderNumber;

	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();

		if (enteredNumber.containsKey(player)) {
			String password = event.getMessage();
			if (orderManager.getPassword(orderNumber).equals(password)) {
				inProcess.remove(player, player.getName());
				enteredNumber.remove(player, player.getName());

				Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {

					@Override
					public void run() {
						player.sendMessage(prefix.PREFIX + "Das angegebene Passwort ist korrekt§7!");
						player.sendMessage(prefix.PREFIX + "Hier die Daten der Auftragsnummer§7: §e" + orderNumber);

						player.sendMessage(prefix.PREFIX + "§e§m--------§r§aDaten§e§m--------");
						player.sendMessage("§e§f");
						
						player.sendMessage(prefix.PREFIX + "§eName§7: §e" + orderManager.getOrderName(orderNumber));
						if (orderManager.getStatus(orderNumber).equalsIgnoreCase("started")) {
							player.sendMessage(prefix.PREFIX + "§eStatus§7: §eIn Arbeit");

						} else if (orderManager.getStatus(orderNumber).equalsIgnoreCase("nearly_finished")) {
							player.sendMessage(prefix.PREFIX + "§eStatus§7: §eFast Fertig");

						} else if (orderManager.getStatus(orderNumber).equalsIgnoreCase("inqueue")) {
							player.sendMessage(prefix.PREFIX + "§eStatus§7: §eIn der Warteschlange");

						} else if (orderManager.getStatus(orderNumber).equalsIgnoreCase("finished")) {
							player.sendMessage(prefix.PREFIX + "§eStatus§7: §eFertig");

						}

						player.sendMessage(prefix.PREFIX + "§eStatus in Prozent§7: §e" + orderManager.getStatusPercent(orderNumber) + "§7%");
						player.sendMessage("§e");
						player.sendMessage(prefix.PREFIX + "§e§m--------§r§aDaten§e§m--------");

					}

				}, 10);

			} else {
				player.sendMessage(prefix.INVALID_PASSWORD);

			}

		}

		if (inProcess.containsKey(player)) {
			if (event.getMessage().equalsIgnoreCase("cancel")) {
				
				Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {

					@Override
					public void run() {
						player.sendMessage(prefix.PREFIX + "§cDer Vorgang wurde erfolgreich abgebrochen§7.");
						player.sendMessage(prefix.PREFIX + "§7Wir wünschen ihnen noch einen schönen Tag!");

					}

				}, 10);

			} else {
				if (orderManager.orderExists(Integer.valueOf(event.getMessage()))) {
					Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							player.sendMessage(prefix.PREFIX + "§aAuftragsnummer ist korrekt§7!");
							orderNumber = Integer.valueOf(event.getMessage());
							player.sendMessage(prefix.PREFIX + "§7Schreiben sie nun das gültige Passwort für die Auftragsnummer§7: §e" + orderNumber);
							enteredNumber.put(player, player.getName());


						}

					}, 10);
					
				} else {
					player.sendMessage(prefix.INVALID_NUMBER);

				}

			}

		}

	}

}
