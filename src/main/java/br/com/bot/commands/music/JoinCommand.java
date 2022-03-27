package br.com.bot.commands.music;

import br.com.bot.commands.ICommand;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.voice.AudioProvider;

public class JoinCommand implements ICommand {

    private AudioProvider audioProvider;

    public JoinCommand (AudioProvider audioProvider) {
        this.audioProvider = audioProvider;
    }

    @Override
    public void execute(MessageCreateEvent event) {
        final Member member = event.getMember()
                .orElse(null);

        if(member != null) {
            final VoiceState voiceState = member.getVoiceState().block();
            if(voiceState != null) {
                final VoiceChannel voiceChannel = voiceState.getChannel().block();
                if(voiceChannel != null) {
                    // join returns a VoiceConnection which would be required if we were
                    // adding disconnection features, but for now we are just ignoring it.
                    voiceChannel.join(spec -> spec.setProvider(this.audioProvider)).block();
                }
            }
        }
    }
}
