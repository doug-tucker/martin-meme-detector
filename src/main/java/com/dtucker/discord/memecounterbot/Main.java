package com.dtucker.discord.memecounterbot;

import com.dtucker.discord.memecounterbot.listeners.MartinPostedPictureEventLisener;
import com.dtucker.discord.memecounterbot.listeners.MessageReceivedEventListener;
import com.dtucker.discord.memecounterbot.listeners.ReadyListener;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by doug on 6/9/16.
 */
public class Main {
    // constants
    protected static final String CLIENT_ID = "190659904917209088";
    protected static final String CLIENT_SECRET = "WGHmAftUvoCbsrr8q507LCkpbXOoc37n";
    protected static final String BOT_USERNAME = "martin-meme-counter-bot#1855";
    protected static final String BOT_ID = "190668073462661128";
    protected static final String BOT_TOKEN = "MTkwNjY4MDczNDYyNjYxMTI4.CjvE-w.N_91s5BZ_kz3rZzPPgTiY_foUjs";

    public static IDiscordClient client;
    public static AtomicInteger memeCounter = new AtomicInteger(0);
    public static AtomicInteger drakeCounter = new AtomicInteger(0);

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String [] args) throws Exception {
        // init app
        client = new ClientBuilder().withToken(BOT_TOKEN).login();

        // register listeners
        client.getDispatcher().registerListener(new ReadyListener());
        client.getDispatcher().registerListener(new MessageReceivedEventListener());
        client.getDispatcher().registerListener(new MartinPostedPictureEventLisener());
    }
}
