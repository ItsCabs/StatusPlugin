package de.pvphub.statusplugin.manager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.pvphub.statusplugin.utils.OrderStatus;

public class OrderManager {

	public File orders = new File("plugins/StatusPlugin", "orders.yml");
	private static FileConfiguration ordersConfiguration;
	
	public void createFile() {
		ordersConfiguration = YamlConfiguration.loadConfiguration(orders);
		updateOrders();
		
	}
	
	public void updateOrders() {
		try {
			ordersConfiguration.save(orders);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
	
	public void addOrder(String name) {
	
		Double randomDouble = Math.random();
		Integer orderNumber = (int) (randomDouble  * 100000 + 1);
		String orderPassword = "PW" + ThreadLocalRandom.current().nextInt(1000, 9999);
				
		ordersConfiguration.set("Orders." + orderNumber + ".name", name);
		ordersConfiguration.set("Orders." + orderNumber + ".status", OrderStatus.INQUEUE.toString());
		ordersConfiguration.set("Orders." + orderNumber + ".password", orderPassword);
		ordersConfiguration.set("Orders." + orderNumber + ".percent", 0);
		
		updateOrders();
		
	}
	
	public String getOrderName(Integer orderNumber) {
		return ordersConfiguration.getString("Orders." + orderNumber + ".name");
		
	}
	
	public String getStatus(Integer orderNumber) {
		return ordersConfiguration.getString("Orders." + orderNumber + ".status");
		
	}
	
	public String getPassword(Integer orderNumber) {
		return ordersConfiguration.getString("Orders." + orderNumber + ".password");
		
	}
	
	public Integer getStatusPercent(Integer orderNumber) {
		return ordersConfiguration.getInt("Orders." + orderNumber + ".percent");
		
	}
	
	public Boolean orderExists(Integer orderNumber) {
		if(ordersConfiguration.getString("Orders." + orderNumber + ".name") != null) {
			return true;
			
		} else {
			return false;
			
		}
		
	}
	
	// Set Methods
	
	public void setStatus(Integer orderNumber, OrderStatus status) {
		ordersConfiguration.set("Orders." + orderNumber + ".status", status.toString());
		updateOrders();
		
	}
	
	public void setPassword(Integer orderNumber, String password) {
		ordersConfiguration.set("Orders." + orderNumber + ".password", password);
		updateOrders();
		
	}
	
	public void setPercent(Integer orderNumber, Integer percent) {
		ordersConfiguration.set("Orders." + orderNumber + ".percent", percent);
		updateOrders();
		
	}
	
}
