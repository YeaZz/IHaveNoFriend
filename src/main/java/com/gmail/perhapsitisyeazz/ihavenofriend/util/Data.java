package com.gmail.perhapsitisyeazz.ihavenofriend.util;

import com.gmail.perhapsitisyeazz.ihavenofriend.IHaveNoFriend;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moderocky.mask.internal.utility.FileManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class Data {

	private final File storingFile = new IHaveNoFriend().storingFile;

	@SuppressWarnings("deprecation")
	public JsonObject getJsonObject(OfflinePlayer player) {
		String playerJsonString = getStringObject(player);
		return (JsonObject) new JsonParser().parse(playerJsonString);
	}

	public String getStringObject(OfflinePlayer player) {
		File playerData = getData(player);
		return FileManager.read(playerData);
	}

	public File getData(OfflinePlayer player) {
		UUID playerUniqueId = player.getUniqueId();
		return new File(storingFile, playerUniqueId + ".json");
	}

	public void saveObject(File file, JsonObject jsonObject) {
		String string = jsonObject.toString();
		FileManager.write(file, string);
	}

	public boolean alreadyFriend(Player sender, OfflinePlayer target) {
		JsonObject senderObject = getJsonObject(sender);
		JsonObject targetObject = getJsonObject(target);
		JsonArray senderJsonArray = senderObject.getAsJsonArray("FriendList");
		JsonArray targetJsonArray = targetObject.getAsJsonArray("FriendList");
		boolean senderBoolean = false;
		for (JsonElement jsonElement : senderJsonArray) {
			if (jsonElement.getAsString().equals(target.getUniqueId().toString())) senderBoolean = true;
		}
		boolean targetBoolean = false;
		for (JsonElement jsonElement : targetJsonArray) {
			if (jsonElement.getAsString().equals(sender.getUniqueId().toString())) targetBoolean = true;
		}
		return senderBoolean && targetBoolean;
	}

	public void addFriendFile(Player sender, OfflinePlayer target) {
		JsonObject senderObject = getJsonObject(sender);
		JsonObject targetObject = getJsonObject(target);
		JsonArray senderJsonArray = senderObject.getAsJsonArray("FriendList");
		JsonArray targetJsonArray = targetObject.getAsJsonArray("FriendList");
		senderJsonArray.add(target.getUniqueId().toString());
		targetJsonArray.add(sender.getUniqueId().toString());
		saveObject(getData(sender), senderObject);
		saveObject(getData(target), targetObject);
	}

	public void removeFriendFile(Player sender, OfflinePlayer target) {
		JsonObject senderObject = getJsonObject(sender);
		JsonObject targetObject = getJsonObject(target);
		JsonArray senderJsonArray = senderObject.getAsJsonArray("FriendList");
		JsonArray targetJsonArray = targetObject.getAsJsonArray("FriendList");
		int senderArrayInt = 0;
		for (JsonElement jsonElement : senderJsonArray) {
			if (jsonElement.getAsString().equals(target.getUniqueId().toString())) {
				senderJsonArray.remove(senderArrayInt);
				break;
			}
			senderArrayInt++;
		}
		int targetArrayInt = 0;
		for (JsonElement jsonElement : targetJsonArray) {
			if (jsonElement.getAsString().equals(sender.getUniqueId().toString())) {
				targetJsonArray.remove(targetArrayInt);
				break;
			}
			targetArrayInt++;
		}
		saveObject(getData(sender), senderObject);
		saveObject(getData(target), targetObject);
	}

	public boolean hasFriendToggle(OfflinePlayer sender) {
		JsonObject object = getJsonObject(sender);
		return object.get("hasFriendRequestToggle").getAsBoolean();
	}
}
