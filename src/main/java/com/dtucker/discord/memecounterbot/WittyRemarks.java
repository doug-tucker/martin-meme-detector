package com.dtucker.discord.memecounterbot;

/**
 * Witty remarks to spice up messages.
 */
public class WittyRemarks {
    // here are my witty remarks so far (they're so few ;_;). To add one, follow the same format of "remark",
    public static final String [] ALL_REMARKS = new String [] {
            "A meme a day keeps the ladies away.",
            "If this were any danker, it would be illegal in 40-something states.",
            "That left me feeling kinda dirty!",
            "Really, this shit again?",
            "ANOTHA ONE",
            "That's enough for today...",
            "OH MY GOD YOUR MOM IS VACUUMING AGAIN WTF!",
            "get back to vacuuming bro",
    };

    /**
     * Uses the current time to randomly choose one of the messages above
     * @return a super witty, randomized remark
     */
    public static String getRandomRemark() {
        final int randomIndex = ((int) System.currentTimeMillis() % ALL_REMARKS.length);
        return ALL_REMARKS[randomIndex];
    }
}
