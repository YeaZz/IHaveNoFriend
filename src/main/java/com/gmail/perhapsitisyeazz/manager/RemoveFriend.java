package com.gmail.perhapsitisyeazz.manager;

import com.gmail.perhapsitisyeazz.util.Data;
import com.gmail.perhapsitisyeazz.util.Message;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class RemoveFriend {

    public static void friendRemoveCommand(Player sender, OfflinePlayer target) {
        if (!Data.alreadyFriend(sender, target)) {
            sender.sendMessage(Message.getIsNotYourFriendMessage(target));
            return;
        }
        Data.removeFriendFile(sender, target);
        sender.sendMessage(Message.getSuccessfullyRemovedMessage(target));
    }
}
