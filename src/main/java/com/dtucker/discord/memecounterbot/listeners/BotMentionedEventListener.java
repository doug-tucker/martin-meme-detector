package com.dtucker.discord.memecounterbot.listeners;

import com.dtucker.discord.memecounterbot.Main;
import com.dtucker.discord.memecounterbot.events.BotMentionedEvent;
import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;

/**
 * Created by doug on 6/11/16.
 */
public class BotMentionedEventListener {
    @EventSubscriber
    public void onMention(BotMentionedEvent event) throws HTTP429Exception, DiscordException, MissingPermissionsException {
        String outboundMessage = "Don't you sass me, " + event.getMessage().getAuthor().mention() + " !";

        new MessageBuilder(Main.client)
                .withChannel(event.getMessage().getChannel())
                .withContent(outboundMessage)
                .build();
    }
}
