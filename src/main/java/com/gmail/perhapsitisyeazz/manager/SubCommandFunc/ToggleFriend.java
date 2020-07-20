package com.gmail.perhapsitisyeazz.manager.SubCommandFunc;

import com.gmail.perhapsitisyeazz.util.Data;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moderocky.mask.internal.utility.FileManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

import static com.gmail.perhapsitisyeazz.IHaveNoFriend.storingFile;

public class ToggleFriend {

    public static void friendToggleCommand(Player sender) {
        JsonObject object = Data.getJsonObject(sender);
        if (Data.hasFriendToggle(sender)) {
            sender.sendMessage(new ComponentBuilder()
                    .append("[").color(ChatColor.DARK_GRAY)
                    .append("Friend").color(ChatColor.DARK_AQUA)
                    .append("] ").color(ChatColor.DARK_GRAY)
                    .append("Nobody can add you as friend anymore.").color(ChatColor.DARK_GREEN)
                    .create());
            object.addProperty("hasFriendRequestToggle", false);
            return;
        }
        sender.sendMessage(new ComponentBuilder()
                .append("[").color(ChatColor.DARK_GRAY)
                .append("Friend").color(ChatColor.DARK_AQUA)
                .append("] ").color(ChatColor.DARK_GRAY)
                .append("Everybody can add you as friend now.").color(ChatColor.DARK_GREEN)
                .create());
        object.addProperty("hasFriendRequestToggle", true);
        Data.saveObject(Data.getData(sender), object);
    }
}
