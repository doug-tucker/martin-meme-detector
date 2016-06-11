package com.dtucker.discord.memecounterbot.events;

import sx.blah.discord.api.Event;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Custom event thrown when there is a message that starts with ^ (tht is, maybe it's a command for us!)
 */
public class BotCommandEvent extends Event {
    private final IMessage message;

    public BotCommandEvent(IMessage message) {
        this.message = message;
    }

    public IMessage getMessage() {
        return message;
    }
}
