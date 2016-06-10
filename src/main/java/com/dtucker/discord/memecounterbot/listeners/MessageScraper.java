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
public class MessageScraper {
    public static final Logger LOGGER = LoggerFactory.getLogger(MessageScraper.class);

    public static final String MARTIN_ID = "190621241869074433";
    public static final String DOUG_ID = "175414741798354944";

    /**
     * This method is intended to check mesages for certain content. If the message fulfils a certain requirement, early
     * return after that (to save cycles).
     * @param event event object containing the message and other useful info.
     * @throws IOException
     */
    @EventSubscriber
    public void onMessagePosted(MessageReceivedEvent event) throws IOException {
        if (martinMemeCheck(event)) {
            return;
        }

        // next, check to see if someone called us out to handle a command or talk shit (future functionality here)
    }

    /**
     * Check to see if Martin posted a meme
     * @param event MessageReceivedEvent
     * @return true if Martin posted a meme, false otherwise
     * @throws IOException
     */
    private boolean martinMemeCheck(MessageReceivedEvent event) throws IOException {
        // first, determine if Martin is the author
        final IMessage message = event.getMessage();
        LOGGER.debug(message.getAuthor().getName() + " just posted a message. ID: " + message.getAuthor().getID());
        if (!MARTIN_ID.equals(message.getAuthor().getID())) {
            // it wasn't Martin. We don't care.
            return false;
        }
        LOGGER.debug("It was Martin!");
        final List<IMessage.Attachment> attachments = message.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
            LOGGER.info("aww shit, Martin posted at least one attachment!");
            for (IMessage.Attachment attachment : attachments) {
                // let's look for clues
                final String contentType = isAttachmentImageShallowCheck(attachment);
                if (contentType.startsWith("image")) {
                    // ok, at this point, we are pretty sure he posted a meme.
                    final boolean isDrake = attachment.getFilename().toLowerCase().contains("drake");
                    event.getClient().getDispatcher().dispatch(new MartinPostedPictureEvent(message, isDrake));
                    return true;
                }
            }
        }
        // If we are here, then Martin posted a boring old non-meme post. :(
        return false;
    }

    /**
     * This method attempts to make a connection to wherever the image is hosted, download it, and analyze it.
     *
     * NOTE: THIS THROWS A 403 WHEN HOSTED ON DISCORD. TODO fix this cause this would be more accurate
     *
     * @param attachment attachment in question
     * @return HTTP-like syntax of content type (ex: "text/html", "image/jpeg")
     * @throws IOException
     */
    private String isAttachmentImageDeepCheck(IMessage.Attachment attachment) throws IOException {
        final URL attachmentURL = new URL(attachment.getUrl());
        final URLConnection connection = attachmentURL.openConnection();
        return URLConnection.guessContentTypeFromStream(connection.getInputStream());
    }

    /**
     * This method takes the filename and attempts to guess what type of file it is based solely on name. No HTTP calls
     * made here.
     * @param attachment attachment in question
     * @return HTTP-like syntax of content type (ex: "text/html", "image/jpeg")
     * @throws IOException
     */
    private String isAttachmentImageShallowCheck(IMessage.Attachment attachment) throws IOException {
        return URLConnection.guessContentTypeFromName(attachment.getFilename());
    }
}
