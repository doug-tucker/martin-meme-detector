package com.dtucker.discord.memecounterbot;

import com.dtucker.discord.memecounterbot.listeners.MartinPostedPictureEventLisener;
import com.dtucker.discord.memecounterbot.listeners.MessageScraper;
import com.dtucker.discord.memecounterbot.listeners.ReadyListener;
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

    public static IDiscordClient client;
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
        LOGGER.debug("Using bot token " + BOT_TOKEN);

        // init app
        client = new ClientBuilder().withToken(BOT_TOKEN).login();

        // register listeners
        client.getDispatcher().registerListener(new ReadyListener());
        client.getDispatcher().registerListener(new MessageScraper());
        client.getDispatcher().registerListener(new MartinPostedPictureEventLisener());

        LOGGER.debug("All listeners have been registered.");
    }
}
