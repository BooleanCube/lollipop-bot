package lollipop.commands;

import lollipop.Command;
import lollipop.Constant;
import lollipop.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class Support implements Command {

    @Override
    public String[] getAliases() {
        return new String[]{"support"};
    }

    @Override
    public String getCategory() {
        return "Miscellaneous";
    }

    @Override
    public String getHelp() {
        return "Find support for issues with lollipop!\nUsage: `" + Constant.PREFIX + getAliases()[0] + "`";
    }

    @Override
    public CommandData getSlashCmd() {
        return Tools.defaultSlashCmd(this);
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {
        event.replyEmbeds(
                new EmbedBuilder()
                        .setTitle("Encountering issues with lollipop?")
                        .setDescription("> Join this [server](https://discord.gg/tEWqCY2fCz)\n> Explain what your issues are in <#984455456464453662>")
                        .setThumbnail("https://cdn-icons-png.flaticon.com/512/2057/2057748.png")
                        .build()
        ).queue();
    }

}
