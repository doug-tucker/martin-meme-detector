package com.dtucker.discord.memecounterbot.events;

import sx.blah.discord.api.Event;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Custom event thrown when there is a message with our bot mentioned in it.
 */
public class BotMentionedEvent extends Event {
    private final IMessage message;

    public BotMentionedEvent(IMessage message) {
        this.message = message;
    }

    public IMessage getMessage() {
        return message;
    }
}
