package com.dtucker.discord.memecounterbot.listeners;

import com.dtucker.discord.memecounterbot.events.MartinPostedPictureEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Listener for *every* message posted in the server. This acts as a filter to our other events that we care about.
 */
public class MessageReceivedEventListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(MessageReceivedEventListener.class);

    public static final String MARTIN_ID = "190621241869074433";

    @EventSubscriber
    public void onMessagePosted(MessageReceivedEvent event) throws IOException {
        // first, determine if Martin did indeed post a sick meme
        final IMessage message = event.getMessage();
        LOGGER.debug(message.getAuthor().getName() + " just posted a message. ID: " + message.getAuthor().getID());
        if (MARTIN_ID.equals(message.getAuthor().getID())) {
            LOGGER.info("It was Martin!");
            final List<IMessage.Attachment> attachments = message.getAttachments();
            if (attachments != null && !attachments.isEmpty()) {
                LOGGER.info("aww shit, Martin posted at least one attachment!");
                for (IMessage.Attachment attachment : attachments) {
                    // let's look for clues
                    final URL attachmentURL = new URL(attachment.getUrl());
                    final URLConnection connection = attachmentURL.openConnection();
                    final String contentType = URLConnection.guessContentTypeFromStream(connection.getInputStream());
                    if (contentType.startsWith("image")) {
                        // ok, at this point, we are pretty sure he posted a meme.
                        final boolean isDrake = attachment.getFilename().toLowerCase().contains("drake");
                        event.getClient().getDispatcher().dispatch(new MartinPostedPictureEvent(message, isDrake));
                    }
                }
            }
        }

        // next, check to see if someone called us out to handle a command or talk shit
    }
}
