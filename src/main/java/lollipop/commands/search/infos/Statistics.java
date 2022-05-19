package lollipop.commands.search.infos;

import lollipop.API;
import lollipop.Tools;
import lollipop.pages.AnimePage;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.io.IOException;

public class Statistics {

    public static void run(ButtonInteractionEvent event, AnimePage page) {
        API api = new API();
        long id = page.animes.get(page.pageNumber-1).malID;
        try {
            MessageEmbed statsEmbed = Tools.statsToEmbed(api.getAnimeStats(id)).build();
            event.replyEmbeds(statsEmbed).setEphemeral(true).queue();
            page.stats.put(page.pageNumber, statsEmbed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
