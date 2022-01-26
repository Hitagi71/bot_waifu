package br.com.misakabot.commands.music;

import br.com.misakabot.commands.ICommand;
import br.com.misakabot.music.TrackScheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

import java.util.Arrays;
import java.util.List;

public class PlayCommand implements ICommand {

    private AudioPlayerManager audioPlayerManager;
    private TrackScheduler trackScheduler;

    public PlayCommand(AudioPlayerManager audioPlayerManager, TrackScheduler trackScheduler) {
        this.audioPlayerManager = audioPlayerManager;
        this.trackScheduler = trackScheduler;
    }


    @Override
    public void execute(MessageCreateEvent event) {
        Message message = event.getMessage();
        final String content = message.getContent();

        final List<String> command = Arrays.asList(content.split(" "));
        audioPlayerManager.loadItem(command.get(1), this.trackScheduler);
    }
}
