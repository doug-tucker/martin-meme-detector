package com.dtucker.discord.memecounterbot.events;

import sx.blah.discord.api.Event;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Custom event for when Martin has posted a meme. A simple POJO containing his original message and this bot's best
 * guess as to whether this is a Drake meme or not.
 */
public class MartinPostedPictureEvent extends Event {
    private final IMessage message;
    private final boolean isDrake;

    public MartinPostedPictureEvent(IMessage message, boolean isDrake) {
        this.message = message;
        this.isDrake = isDrake;
    }

    public IMessage getMessage() {
        return message;
    }

    public boolean isDrake() {
        return isDrake;
    }

}
