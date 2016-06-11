package com.dtucker.discord.memecounterbot.listeners;

import com.dtucker.discord.memecounterbot.Main;
import com.dtucker.discord.memecounterbot.events.BotCommandEvent;
import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by doug on 6/11/16.
 */
public class BotCommandEventListener {
    public static final Map<String, String> commandNameToDescription = new HashMap<>();

    static {
        commandNameToDescription.put("test", "test response");
    }

    @EventSubscriber
    public void onCommand(BotCommandEvent event) throws HTTP429Exception, DiscordException, MissingPermissionsException {
        // extract the command name from the message
        final String [] words = event.getMessage().getContent().split(" ");
        final String command = words[0].substring(1); // trim the leading ^
        final String description = commandNameToDescription.get(command);
        if (null != description) {
            new MessageBuilder(Main.client)
                    .withChannel(event.getMessage().getChannel())
                    .withContent(description)
                    .build();
        }
    }
}
