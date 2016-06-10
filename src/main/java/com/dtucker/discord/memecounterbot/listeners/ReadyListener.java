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
public class ReadyListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadyListener.class);

    @EventSubscriber
    public void onReady(ReadyEvent event) {
        try {
            Main.client.changeUsername("Martin Meme Counter");
            LOGGER.debug("Name change successful.");
        } catch (DiscordException | HTTP429Exception e) {
            LOGGER.error("Couldn't change username!", e);
        }
        LOGGER.info("Bot is ready to go!");
    }
}
