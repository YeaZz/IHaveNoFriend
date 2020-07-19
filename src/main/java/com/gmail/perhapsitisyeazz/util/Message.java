package com.gmail.perhapsitisyeazz.util;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {

	public static void sendHelpMessage(CommandSender sender) {
		ComponentBuilder builder = new ComponentBuilder();
		if (sender instanceof Player) {
			builder
					.append("Running ").color(ChatColor.DARK_GREEN)
					.append("IHaveNoFriend v").color(ChatColor.AQUA)
					.append(IHaveNoFriend.getInstance().getVersion())
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
					.append("IHaveNoFriend v").color(ChatColor.AQUA)
					.append(IHaveNoFriend.getInstance().getVersion())
					.append(".").color(ChatColor.DARK_GREEN)
					.create());
			sender.sendMessage(new ComponentBuilder()
					.append("Use ")
					.append("/friend help").color(ChatColor.GREEN)
					.append(" to see all commands.").retain(ComponentBuilder.FormatRetention.NONE).reset()
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
}
