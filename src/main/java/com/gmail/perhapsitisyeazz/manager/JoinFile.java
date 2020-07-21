package com.gmail.perhapsitisyeazz.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moderocky.mask.internal.utility.FileManager;
import org.bukkit.entity.Player;

import java.io.File;

import static com.gmail.perhapsitisyeazz.IHaveNoFriend.storingFile;

public class JoinFile {

	public static JsonObject getJsonObject(Player player) {
		JsonObject object = new JsonObject();
		object.addProperty("UniqueID", player.getUniqueId().toString());
		object.addProperty("Username", player.getName());
		object.addProperty("hasFriendRequestToggle", true);
		object.add("FriendList", new JsonArray());
		return object;
	}

	public static void createPlayerFile(Player player) {
		String uniqueID = player.getUniqueId().toString();
		if (new File(storingFile + uniqueID).exists()) return;
		String jsonObjectToString = getJsonObject(player).toString();
		FileManager.write(new File(storingFile, uniqueID + ".json"), jsonObjectToString);
	}
}
