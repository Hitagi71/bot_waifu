package br.com.misakabot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventListener;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackScheduler implements AudioLoadResultHandler, AudioEventListener {

    private final Logger logger = LoggerFactory.getLogger(TrackScheduler.class);

    private final AudioPlayer player;

    public TrackScheduler(final AudioPlayer player) {
        this.player = player;
    }

    @Override
    public void trackLoaded(AudioTrack audioTrack) {
        // LavaPlayer found an audio source for us to play

    }

    @Override
    public void playlistLoaded(AudioPlaylist audioPlaylist) {
    }

    @Override
    public void noMatches() {
        // LavaPlayer did not found any audio to extract

    }

    @Override
    public void loadFailed(FriendlyException e) {
        // LavaPlayer could not parse an audio source for some reason
    }

    @Override
    public void onEvent(AudioEvent audioEvent) {

    }
}
