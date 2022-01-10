package lollipop.commands;

import lollipop.CONSTANT;
import lollipop.Command;
import lollipop.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Punch implements Command {
    @Override
    public String[] getAliases() {
        return new String[]{"punch"};
    }

    @Override
    public String getCategory() {
        return "Roleplay";
    }

    @Override
    public String getHelp() {
        return "Punch somebody!\nUsage: `" + CONSTANT.PREFIX + getAliases()[0] + " [user]`";
    }

    @Override
    public void run(List<String> args, MessageReceivedEvent event) {
        if(args.isEmpty()) { Tools.wrongUsage(event.getTextChannel(), this); return; }
        String[] gifs = {"https://tenor.com/view/punchy-one-punch-man-anime-punch-fight-gif-16189288", "https://tenor.com/view/one-punch-man-punch-serious-gif-12250021", "https://tenor.com/view/si-gif-22925940", "https://tenor.com/view/one-punch-man-saitama-gif-8618419", "https://tenor.com/view/one-punch-man-gif-23006509"};
        Member target = Tools.getEffectiveMember(event.getGuild(), String.join(" ", args));
        if(target == null) {
            event.getChannel().sendMessageEmbeds(new EmbedBuilder().setDescription("Could not find the specified member!").setColor(Color.red).build()).queue();
            return;
        }
        if(Objects.requireNonNull(event.getMember()).getIdLong() == target.getIdLong()) {
            event.getChannel().sendMessageEmbeds(new EmbedBuilder().setDescription("You can't use Roleplay Commands on yourself!").setColor(Color.red).build()).queue();
            return;
        }
        event.getChannel().sendMessage("**Anybody in my way... gets punched.**\n" + target.getAsMention() + " was punched by " + event.getMember().getAsMention()).queue();
        event.getChannel().sendMessage(gifs[(int)(Math.random()*gifs.length)]).queue();
    }
}