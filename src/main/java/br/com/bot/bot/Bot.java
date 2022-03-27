package br.com.bot.bot;

import br.com.bot.commands.ICommand;
import br.com.bot.commands.anime.AnimeCommand;
import br.com.bot.commands.music.PlayCommand;
import br.com.bot.commands.music.JoinCommand;
import br.com.bot.music.LavaPlayerAudioProvider;
import br.com.bot.music.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.voice.AudioProvider;

import java.util.HashMap;
import java.util.Map;

public class Bot {
    private static final Map<String, ICommand> commands = new HashMap<>();

    public static void main(String[] args) {
        //Creates AudioPlayer instances and translates URLS to AudioTrack instances
        final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();

        // This is an optimization strategy that Discord4J can utilize
        // It is not important to understand
        playerManager.getConfiguration()
                .setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);

        // Allow playerManager to parse remote sources like Youtube links
        AudioSourceManagers.registerRemoteSources(playerManager);

        //Create an AudioPlayer so Discord4J can receive audio data
        final AudioPlayer player = playerManager.createPlayer();

        AudioProvider provider = new LavaPlayerAudioProvider(player);

        final TrackScheduler scheduler = new TrackScheduler(player);

        commands.put("join", new JoinCommand(provider));
        commands.put("play", new PlayCommand(playerManager, scheduler));
        commands.put("anime", new AnimeCommand());

        final GatewayDiscordClient client = DiscordClientBuilder.create(args[0])
                .build()
                .login()
                .block();

        client.getEventDispatcher()
                .on(ReadyEvent.class)
                .subscribe(readyEvent -> {
                    System.out.println("Ready!!!");
                });

        client.getEventDispatcher()
                .on(MessageCreateEvent.class)
                // subscribe is like block, in that it will *request* for action
                // to be done, but instead of blocking the thread, waiting for it
                // to finish, it will just execute the results asynchronously.
                .subscribe(event -> {
                    // Message.getContent() is a String
                    final String content = event.getMessage().getContent();

                    for (final Map.Entry<String, ICommand> entry : commands.entrySet()) {
                        // We will be using ! as our prefix to any command in the system
                        if(content.startsWith('!' + entry.getKey())) {
                            entry.getValue().execute(event);
                            break;
                        }
                    }
                });

        client.onDisconnect().block();
    }
}
