package com.gmail.perhapsitisyeazz.manager.SubCommandFunc;

import com.gmail.perhapsitisyeazz.util.Data;
import com.gmail.perhapsitisyeazz.util.Message;
import com.google.gson.JsonObject;
import org.bukkit.entity.Player;

public class ToggleFriend {

    public static void friendToggleCommand(Player sender) {
        JsonObject object = Data.getJsonObject(sender);
        if (Data.hasFriendToggle(sender)) {
            sender.sendMessage(Message.getNobodyCanAddFriendMessage());
            object.addProperty("hasFriendRequestToggle", false);
            return;
        }
        sender.sendMessage(Message.getEveryCanAddFriendMessage());
        object.addProperty("hasFriendRequestToggle", true);
        Data.saveObject(Data.getData(sender), object);
    }
}
