package com.gmail.perhapsitisyeazz.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moderocky.mask.internal.utility.FileManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

import static com.gmail.perhapsitisyeazz.IHaveNoFriend.storingFile;

public class Data {

	@SuppressWarnings("deprecation")
	public static JsonObject getJsonObject(OfflinePlayer player) {
		String playerJsonString = getStringObject(player);
		return (JsonObject) new JsonParser().parse(playerJsonString);
	}

	public static String getStringObject(OfflinePlayer player) {
		File playerData = getData(player);
		return FileManager.read(playerData);
	}

	public static File getData(OfflinePlayer player) {
		UUID playerUniqueId = player.getUniqueId();
		return new File(storingFile, playerUniqueId + ".json");
	}

	public static void saveObject(File file, JsonObject jsonObject) {
		String string = jsonObject.toString();
		FileManager.write(file, string);
	}

	public static boolean alreadyFriend(Player sender, OfflinePlayer target) {
		JsonObject senderObject = Data.getJsonObject(sender);
		JsonObject targetObject = Data.getJsonObject(target);
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

	public static void addFriendFile(Player sender, OfflinePlayer target) {
		JsonObject senderObject = Data.getJsonObject(sender);
		JsonObject targetObject = Data.getJsonObject(target);
		JsonArray senderJsonArray = senderObject.getAsJsonArray("FriendList");
		JsonArray targetJsonArray = targetObject.getAsJsonArray("FriendList");
		senderJsonArray.add(target.getUniqueId().toString());
		targetJsonArray.add(sender.getUniqueId().toString());
		Data.saveObject(Data.getData(sender), senderObject);
		Data.saveObject(Data.getData(target), targetObject);
	}

	public static void removeFriendFile(Player sender, OfflinePlayer target) {
		JsonObject senderObject = Data.getJsonObject(sender);
		JsonObject targetObject = Data.getJsonObject(target);
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
		Data.saveObject(Data.getData(sender), senderObject);
		Data.saveObject(Data.getData(target), targetObject);
	}

	public static boolean hasFriendToggle(OfflinePlayer sender) {
		JsonObject object = Data.getJsonObject(sender);
		return object.get("hasFriendRequestToggle").getAsBoolean();
	}
}
