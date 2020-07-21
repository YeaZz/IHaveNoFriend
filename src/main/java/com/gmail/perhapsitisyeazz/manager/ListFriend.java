package com.gmail.perhapsitisyeazz.manager;

import com.gmail.perhapsitisyeazz.util.Data;
import com.gmail.perhapsitisyeazz.util.Message;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ListFriend {

	public static void friendListFriend(Player sender) {
		JsonObject senderObject = Data.getJsonObject(sender);
		JsonArray jsonArray = senderObject.get("FriendList").getAsJsonArray();
		if (jsonArray.size() == 0) {
			sender.sendMessage(Message.getNoFriendMessage());
			return;
		}
		ComponentBuilder builder = new ComponentBuilder();
		builder
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("Here is your friend list: ").color(ChatColor.DARK_GREEN)
				.append("(").color(ChatColor.DARK_GRAY)
				.append(String.valueOf(jsonArray.size())).color(ChatColor.AQUA)
				.append(")").color(ChatColor.DARK_GRAY);
		for (JsonElement array : jsonArray) {
			UUID uuid = UUID.fromString(array.getAsString());
			OfflinePlayer friend = Bukkit.getPlayer(uuid);
			if (friend == null) continue;
			builder
					.append("\n » ").color(ChatColor.DARK_AQUA)
					.append(friend.getName()).color(ChatColor.GREEN);
			if (friend.isOnline()) builder
					.append(" (").color(ChatColor.DARK_GRAY)
					.append("Online").color(ChatColor.AQUA)
					.append(")").color(ChatColor.DARK_GRAY);
		}
		sender.sendMessage(builder.create());
	}
}