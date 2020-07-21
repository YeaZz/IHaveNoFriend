package com.gmail.perhapsitisyeazz.manager;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import com.gmail.perhapsitisyeazz.util.Message;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class DenyFriend {

    public static void friendDenyCommand(Player sender, OfflinePlayer target) {
        HashMap<UUID, UUID> friendRequest = IHaveNoFriend.friendRequest;
        if (!friendRequest.containsKey(target.getUniqueId()) && !friendRequest.containsValue(sender.getUniqueId())) {
            sender.sendMessage(Message.getInvalidRequestMessage());
            return;
        }
        friendRequest.remove(target.getUniqueId());
        sender.sendMessage(Message.getSuccessfullyDeclinedMessage(target));
    }
}
