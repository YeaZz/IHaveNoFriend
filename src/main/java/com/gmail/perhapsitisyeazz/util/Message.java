package com.gmail.perhapsitisyeazz.util;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {

	private static final IHaveNoFriend instance = IHaveNoFriend.getInstance();

	public static void sendHelpMessage(CommandSender sender) {
		ComponentBuilder builder = new ComponentBuilder();
		if (sender instanceof Player) {
			builder
					.append("Running ").color(ChatColor.DARK_GREEN)
					.append(instance.getName()).color(ChatColor.AQUA)
					.append(" v")
					.append(instance.getVersion())
					.append(".").color(ChatColor.DARK_GREEN).reset()
					.append("\nUse ")
					.append("/friend help").color(ChatColor.GREEN)
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Click to execute.", ChatColor.GRAY)))
					.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend help"))
					.append(" to see all commands.").retain(ComponentBuilder.FormatRetention.NONE).reset();
			if (sender.isOp()) {
				builder
						.append("\nBuilt on the ").color(ChatColor.DARK_AQUA).italic(true)
						.append("Mask").color(ChatColor.GREEN).italic(true)
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Click to copy", ChatColor.GRAY)))
						.event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://gitlab.com/Pandaemonium/Mask"))
						.append(" framework.").retain(ComponentBuilder.FormatRetention.NONE).reset().color(ChatColor.DARK_AQUA).italic(true);
			}
			sender.sendMessage(builder.create());
		} else {
			sender.sendMessage(new ComponentBuilder()
					.append("Running ").color(ChatColor.DARK_GREEN)
					.append(instance.getName()).color(ChatColor.AQUA)
					.append(" v")
					.append(instance.getVersion())
					.append(".").color(ChatColor.DARK_GREEN)
					.create());
			sender.sendMessage(new ComponentBuilder()
					.append("Use ")
					.append("/friend help").color(ChatColor.GREEN)
					.append(" to see all commands.").color(ChatColor.WHITE)
					.create());
			sender.sendMessage(new ComponentBuilder()
					.append("Built on the ").color(ChatColor.DARK_AQUA)
					.append("Mask").color(ChatColor.GREEN)
					.append(" framework.").color(ChatColor.DARK_AQUA)
					.create());
			sender.sendMessage(new ComponentBuilder()
					.append("(").color(ChatColor.DARK_GRAY)
					.append("https://gitlab.com/Pandaemonium/Mask").color(ChatColor.GREEN)
					.append(")").color(ChatColor.DARK_GRAY)
					.create());
		}
	}

	public static BaseComponent[] getSenderRequestMessage(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("Friend request send to ").color(ChatColor.DARK_GREEN)
				.append(player.getName()).color(ChatColor.AQUA)
				.append(".").color(ChatColor.DARK_GREEN)
				.append("\n").append(player.getName()).color(ChatColor.AQUA)
				.append("has 60 seconds to accept your request.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getTargetRequestMessage(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("You received a friend request from ").color(ChatColor.DARK_GREEN)
				.append(player.getName()).color(ChatColor.AQUA)
				.append("\nYou have ").color(ChatColor.DARK_GREEN)
				.append("60 seconds").color(ChatColor.AQUA)
				.append(" left to accept.\n         ")
				.append("[Yes]").color(ChatColor.GREEN)
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Click to execute")))
				.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend accept " + player.getName()))
				.append("         ").retain(ComponentBuilder.FormatRetention.NONE).reset()
				.append("[No]").color(ChatColor.RED)
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Click to execute")))
				.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend deny " + player.getName()))
				.create();
	}

	public static BaseComponent[] getInvalidRequestMessage() {
		return new ComponentBuilder()
				.append("This request is invalid or has expired.").color(ChatColor.RED)
				.create();
	}

	public static BaseComponent[] getFriendRequestExpired(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("Friend request from ").color(ChatColor.DARK_GREEN)
				.append(player.getName()).color(ChatColor.AQUA)
				.append(" expired.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getRequestAlreadySend(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("Request already sent to ").color(ChatColor.DARK_GREEN)
				.append(player.getName()).color(ChatColor.AQUA)
				.append(".").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getIsNotYourFriendMessage(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append(player.getName()).color(ChatColor.AQUA)
				.append(" is not your friend.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getIsAlreadyYourFriendMessage(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append(player.getName()).color(ChatColor.AQUA)
				.append(" is already your friend.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getSuccessfullyAddedMessage(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append(player.getName()).color(ChatColor.AQUA)
				.append(" has been added to your friend list.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getCannotAddFriendMessage() {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("You cannot add this player to your friend list.").color(ChatColor.RED)
				.create();
	}

	public static BaseComponent[] getAddedInFriendListMessage(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append(player.getName()).color(ChatColor.AQUA)
				.append(" has been added to your friend list.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getSuccessfullyDeclinedMessage(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("You have declined the friend request from ").color(ChatColor.DARK_GREEN)
				.append(player.getName()).color(ChatColor.AQUA)
				.create();
	}

	public static BaseComponent[] getSuccessfullyRemovedMessage(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("You have successfully removed ").color(ChatColor.DARK_GREEN)
				.append(player.getName()).color(ChatColor.AQUA)
				.append(" from your friend list.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getEveryCanAddFriendMessage() {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("Everybody can add you as friend now.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getNobodyCanAddFriendMessage() {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append("Nobody can add you as friend anymore.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getDoesNotAcceptFriendMessage(OfflinePlayer player) {
		return new ComponentBuilder()
				.append("[").color(ChatColor.DARK_GRAY)
				.append("Friend").color(ChatColor.DARK_AQUA)
				.append("] ").color(ChatColor.DARK_GRAY)
				.append(player.getName()).color(ChatColor.AQUA)
				.append(" doesn't accept friend request.").color(ChatColor.DARK_GREEN)
				.create();
	}

	public static BaseComponent[] getNoFriendMessage() {
		return new ComponentBuilder()
				.append("You do not have friends :(").color(ChatColor.RED)
				.create();
	}
}
