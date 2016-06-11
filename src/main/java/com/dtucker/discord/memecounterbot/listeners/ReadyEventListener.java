package com.dtucker.discord.memecounterbot.listeners;

import com.dtucker.discord.memecounterbot.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;

/**
 * A simple event that's thrown to let our app know that we are ready to go (logged in to server, etc).
 */
public class ReadyEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadyEventListener.class);

    public static final String [] BOT_NAMES = {
            "Doug Jr.",
            "lilpoppa1003",
            "Martin Meme Counter"
    };

    @EventSubscriber
    public void onReady(ReadyEvent event) {
        String randomName = BOT_NAMES[((int)System.currentTimeMillis() % BOT_NAMES.length)];
        try {
            event.getClient().changeUsername(randomName);
            LOGGER.debug("Name change successful. Chose: " + randomName);
        } catch (DiscordException | HTTP429Exception e) {
            LOGGER.error("Couldn't change username!", e);
        }
        LOGGER.info("Bot is ready to go!");
    }
}
