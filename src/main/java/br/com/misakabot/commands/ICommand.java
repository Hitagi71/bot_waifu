package br.com.misakabot.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

import java.io.UnsupportedEncodingException;

public interface ICommand {
    void execute(MessageCreateEvent event) ;
}
