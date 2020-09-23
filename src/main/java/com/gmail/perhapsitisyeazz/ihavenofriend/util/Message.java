package com.gmail.perhapsitisyeazz.ihavenofriend.util;

import com.gmail.perhapsitisyeazz.ihavenofriend.IHaveNoFriend;
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

	private final IHaveNoFriend instance = IHaveNoFriend.getInstance();
	private final Utils utils = new Utils();

	private final String logo = "&8[&3Friend&8] ";

	public void sendHelpMessage(CommandSender sender) {
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

	public BaseComponent[] getSenderRequestMessage(OfflinePlayer player) {
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

	public BaseComponent[] getTargetRequestMessage(OfflinePlayer player) {
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

	public String getInvalidRequestMessage() {
		return utils.getColMsg(logo + "&cThis request is invalid or has expired.");
	}

	public String getFriendRequestExpired(OfflinePlayer player) {
		return utils.getColMsg(logo + "&2Friend request from &b" + player.getName() + " &2expired.");
	}

	public String getRequestAlreadySend(OfflinePlayer player) {
		return utils.getColMsg(logo + "&2Request already sent to &b" + player.getName() + "&b.");
	}

	public String getIsNotYourFriendMessage(OfflinePlayer player) {
		return utils.getColMsg(logo + "&b" + player.getName() + " &2is not your friend.");
	}

	public String getIsAlreadyYourFriendMessage(OfflinePlayer player) {
		return utils.getColMsg(logo + "&b" + player.getName() + " &2is already your friend.");
	}

	public String getSuccessfullyAddedMessage(OfflinePlayer player) {
		return utils.getColMsg(logo + "&b" + player.getName() + " &2has been added to your friend list.");
	}

	public String getCannotAddFriendMessage() {
		return utils.getColMsg(logo + "&cYou cannot add this player to your friend list.");
	}

	public String getAddedInFriendListMessage(OfflinePlayer player) {
		return utils.getColMsg(logo + "&b" + player.getName() + " &2has been added to your friend list.");
	}

	public String getSuccessfullyDeclinedMessage(OfflinePlayer player) {
		return utils.getColMsg(logo + "&2You have declined the friend request from &b" + player.getName() + "&2.");
	}

	public String getSuccessfullyRemovedMessage(OfflinePlayer player) {
		return utils.getColMsg(logo + "&2You have successfully removed &b" + player.getName() + " &2from your friend list.");
	}

	public String getEveryCanAddFriendMessage() {
		return utils.getColMsg(logo + "&2Everybody can add you as friend now.");
	}

	public String getNobodyCanAddFriendMessage() {
		return utils.getColMsg(logo + "&2Nobody can add you as friend anymore.");
	}

	public String getDoesNotAcceptFriendMessage(OfflinePlayer player) {
		return utils.getColMsg(logo + "&b" + player.getName() + " &2doesn't accept friend request.");
	}

	public String getNoFriendMessage() {
		return utils.getColMsg(logo +"&cYou do not have friends :(");
	}
}
