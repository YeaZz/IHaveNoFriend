package com.gmail.perhapsitisyeazz.manager.SubCommandFunc;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import com.gmail.perhapsitisyeazz.util.Data;
import com.gmail.perhapsitisyeazz.util.Message;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class AddFriend {

    private static final IHaveNoFriend instance = IHaveNoFriend.getInstance();

    public static void friendAddCommand(Player sender, OfflinePlayer target) {
        HashMap<UUID, UUID> friendRequest = IHaveNoFriend.friendRequest;
        UUID sID = sender.getUniqueId();
        UUID tID = target.getUniqueId();
        if (sender == target) {
            sender.sendMessage(Message.getCannotAddFriendMessage());
            return;
        }
        if (Data.alreadyFriend(sender, target)) {
            sender.sendMessage(Message.getIsAlreadyYourFriendMessage(target));
            return;
        }
        if (!Data.hasFriendToggle(target)) {
            sender.sendMessage(Message.getDoesNotAcceptFriendMessage(target));
            return;
        }
        if (friendRequest.containsKey(sID) && friendRequest.containsValue(tID)) {
            sender.sendMessage(Message.getRequestAlreadySend(target));
            return;
        }
        if (friendRequest.containsKey(tID) && friendRequest.containsValue(sID)) {
            friendRequest.remove(tID, sID);
            Data.addFriendFile(sender, target);
            sender.sendMessage(Message.getAddedInFriendListMessage(target));
            if (target.isOnline()) ((Player) target).sendMessage(Message.getAddedInFriendListMessage(sender));
            return;
        }
        friendRequest.put(sID, tID);
        sender.sendMessage(Message.getSenderRequestMessage(target));
        if (target.isOnline()) ((Player) target).sendMessage(Message.getTargetRequestMessage(sender));
        BukkitRunnable runnable;
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (friendRequest.containsKey(sID) && friendRequest.containsValue(tID)) {
                    friendRequest.remove(sID, tID);
                    sender.sendMessage(Message.getFriendRequestExpired(target));
                    if (target.isOnline()) ((Player) target).sendMessage(Message.getFriendRequestExpired(sender));
                }
            }
        };
        runnable.runTaskLater(instance, 1200);
    }
}
