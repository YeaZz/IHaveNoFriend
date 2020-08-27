package com.gmail.perhapsitisyeazz.ihavenofriend.manager;

import com.gmail.perhapsitisyeazz.ihavenofriend.IHaveNoFriend;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moderocky.mask.internal.utility.FileManager;
import org.bukkit.entity.Player;

import java.io.File;

public class JoinFile {

	private final File storingFile = new IHaveNoFriend().storingFile;

	public JsonObject getJsonObject(Player player) {
		JsonObject object = new JsonObject();
		object.addProperty("UniqueID", player.getUniqueId().toString());
		object.addProperty("Username", player.getName());
		object.addProperty("hasFriendRequestToggle", true);
		object.add("FriendList", new JsonArray());
		return object;
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void createPlayerFile(Player player) {
		String uniqueID = player.getUniqueId().toString();
		if (new File(storingFile + uniqueID).exists()) return;
		if (!storingFile.exists()) storingFile.mkdirs();
		String jsonObjectToString = getJsonObject(player).toString();
		FileManager.write(new File(storingFile, uniqueID + ".json"), jsonObjectToString);
	}
}
