package com.dtucker.discord.memecounterbot.listeners;

import com.dtucker.discord.memecounterbot.Main;
import com.dtucker.discord.memecounterbot.WittyRemarks;
import com.dtucker.discord.memecounterbot.events.MartinPostedPictureEvent;
import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

/**
 * Listener that replies to Martin's memes.
 */
public class MartinPostedPictureEventLisener {
    @EventSubscriber
    public void onMeme(MartinPostedPictureEvent event) throws HTTP429Exception, DiscordException, MissingPermissionsException {
        // incrememnt our global meme/Drake counters
        int currentMemeCount = Main.memeCounter.incrementAndGet();
        int drakeCount = Main.drakeCounter.get();
        if (event.isDrake()) {
            drakeCount = Main.drakeCounter.incrementAndGet();
        }

        // build and send our reply
        String wittyRemark = WittyRemarks.getRandomRemark();

        String outboundMessage = "Sick " +
                (event.isDrake() ? "Drake " : "") +
                "meme, bro. That's " + currentMemeCount + " memes since I've been alive, " +
                drakeCount + " of which were almost certainly Drake memes. My reaction: "
                + wittyRemark;

        new MessageBuilder(Main.client)
                .withChannel(event.getMessage().getChannel())
                .withContent(outboundMessage)
                .build();
    }
}
