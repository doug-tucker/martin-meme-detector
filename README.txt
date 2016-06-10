MARTIN MEME COUNTER

Prerequisites to building and running this awesome bot:
- Java 8 JDK: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Apache Maven: https://maven.apache.org/

Build instructions:
- For the first time running it (and every other time a new version of code is pushed), run the following command in a
  terminal:
      mvn -U clean install
  For any subsequent build, the `-U` may be removed.

Run instructions:
- After building, look for the file ./target/memecounterbot-{VERSION}.jar (where VERSION is the version number, ex:
  "1.0-SNAPSHOT"). Run that file using the following command from a command line:
      java -jar ./target/memecounterbot-VERSION.jar {BOT_TOKEN}
  Note: you'll need the bot's token to actually run this bot on your machine. Ping @bigpoppa2006 if you want it
- Logs will be printed to the console by default.
