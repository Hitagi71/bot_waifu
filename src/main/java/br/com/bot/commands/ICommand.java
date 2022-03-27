package br.com.bot.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

public interface ICommand {
    void execute(MessageCreateEvent event) ;
}
