package com.dtucker.discord.memecounterbot;

import com.dtucker.discord.memecounterbot.listeners.*;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main class that builds our client, connects us with Discord, and registers our listeners.
 */
public class Main {
    protected static String BOT_TOKEN = "";

    public static final String COMMAND_PREFIX = "-";

    public static IDiscordClient client;

    // use Atomic versions of these since we are working with an asynchronous event-based framework. This ensures they
    // don't stomp on top of each other if Martin simultaneously posts multiple memes. Highly unlikely, but will be
    // useful if we expand to multiple users later and/or Martin goes meme crazy.
    public static AtomicInteger memeCounter = new AtomicInteger(0);
    public static AtomicInteger drakeCounter = new AtomicInteger(0);

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String [] args) throws Exception {
        // grab our bot token from the passed in args
        if (args.length != 1) {
            LOGGER.error("No bot token passed in via command line. Exiting.");
            System.exit(1);
        }

        BOT_TOKEN = args[0];
        LOGGER.debug("Using bot token ending in " + BOT_TOKEN.substring(BOT_TOKEN.length() - 5));

        // init app
        client = new ClientBuilder().withToken(BOT_TOKEN).login();

        // register listeners
        client.getDispatcher().registerListener(new ReadyEventListener());
        client.getDispatcher().registerListener(new MessageScraper());
        client.getDispatcher().registerListener(new MartinPostedPictureEventLisener());
        client.getDispatcher().registerListener(new BotMentionedEventListener());
        client.getDispatcher().registerListener(new BotCommandEventListener());

        LOGGER.debug("All listeners have been registered.");

        // program continues to run until killed manually
    }
}
