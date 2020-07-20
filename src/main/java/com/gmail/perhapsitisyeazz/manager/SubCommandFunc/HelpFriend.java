package com.gmail.perhapsitisyeazz.manager.SubCommandFunc;

import com.gmail.perhapsitisyeazz.IHaveNoFriend;
import com.moderocky.mask.command.Commander;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class HelpFriend {

    private static final IHaveNoFriend instance = IHaveNoFriend.getInstance();

    public static void friendHelpCommand(CommandSender sender, Commander<?> commander) {
        if (sender instanceof Player) {
            ComponentBuilder builder = new ComponentBuilder();
            builder.append("Running ").color(ChatColor.DARK_GREEN);
            builder.append(instance.getName()).color(ChatColor.AQUA);
            builder.append(" v");
            builder.append(instance.getVersion());
            builder.append(".").color(ChatColor.DARK_GREEN);
            for (Map.Entry<String, String> entry : commander.getPatternDescriptions().entrySet()) {
                String pattern = entry.getKey();
                String desc = entry.getValue();
                if (!pattern.contains("help")) {
                    String command = "/" + commander.getCommand();
                    String arg = pattern.split(" ")[0].substring(0, 1).toUpperCase() + pattern.split(" ")[0].substring(1);
                    builder
                            .append("\n» ").color(ChatColor.DARK_AQUA)
                            .append("/" + commander.getCommand() + " " + pattern).color(ChatColor.GREEN)
                            .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(
                                            ChatColor.DARK_GREEN + "Command: " + ChatColor.AQUA + arg +
                                            ChatColor.DARK_GREEN + "\nDescription: " + ChatColor.AQUA + desc +
                                            ChatColor.DARK_GREEN + "\nUsage: " + ChatColor.AQUA + command + " " + pattern +
                                            "\n \n" + ChatColor.GRAY + "Click to execute.")))
                            .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command + " " + pattern.split(" ")[0] + " "))
                            .append(" ").retain(ComponentBuilder.FormatRetention.NONE).reset();
                }
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
                    .append("Only executable by players.").color(ChatColor.RED)
                    .create());
            for (String pattern : commander.getPatterns()) {
                if (!pattern.contains("help")) {
                    sender.sendMessage(new ComponentBuilder()
                            .append("» ").color(ChatColor.DARK_AQUA)
                            .append("/" + commander.getCommand() + " " + pattern).color(ChatColor.GREEN)
                            .append(" ").retain(ComponentBuilder.FormatRetention.NONE).reset()
                            .create());
                }
            }
        }
    }
}
