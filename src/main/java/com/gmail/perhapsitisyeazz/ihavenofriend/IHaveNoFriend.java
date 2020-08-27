package com.gmail.perhapsitisyeazz.ihavenofriend;

import com.gmail.perhapsitisyeazz.ihavenofriend.command.MainCommand;
import com.gmail.perhapsitisyeazz.ihavenofriend.listener.JoinEvent;
import com.moderocky.mask.template.BukkitPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class IHaveNoFriend extends BukkitPlugin {

    private static IHaveNoFriend instance;

    public static IHaveNoFriend getInstance() {
        return instance;
    }

    public final HashMap<UUID, UUID> friendRequest = new HashMap<>();

    public final File storingFile = new File("plugins/IHaveNoFriend/PlayerData/");

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void startup() {
        instance = this;
        if (!storingFile.exists()) storingFile.mkdirs();
        register(
                new MainCommand()
        );
        register(
                new JoinEvent()
        );
    }

    @Override
    public void disable() {
        instance = null;
    }
}