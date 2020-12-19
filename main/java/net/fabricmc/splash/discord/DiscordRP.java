package net.fabricmc.splash.discord;

import net.fabricmc.splash.discord2.net.arikia.dev.drpc.DiscordEventHandlers;
import net.fabricmc.splash.discord2.net.arikia.dev.drpc.DiscordRPC;
import net.fabricmc.splash.discord2.net.arikia.dev.drpc.DiscordRichPresence;
import net.fabricmc.splash.discord2.net.arikia.dev.drpc.DiscordUser;
import net.fabricmc.splash.discord2.net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {

    private boolean running = true;
    private long created = 0;


    public void start() {
        this.created = System.currentTimeMillis();
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
            @Override
            public void apply(DiscordUser user) {
                System.out.println("Welcome" + user.username + "#" + user.discriminator + ".");
                update("booting up...", "");
            }
        }).build();
        DiscordRPC.discordInitialize("719233370823065630", handlers, true);

        new Thread("Discord RPC Callback") {
            @Override
            public void run() {
                while(running) {
                    DiscordRPC.discordRunCallbacks();
                }
            }
        }.start();
    }

    public void shutdown() {
        running = false;
        DiscordRPC.discordShutdown();
    }

    public void update(String firstline, String secondline) {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondline);
        b.setBigImage("large", "");
        b.setDetails(firstline);
        b.setStartTimestamps(created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
}
