package lollipop;

import awatch.models.*;
import awatch.models.Character;
import mread.model.Manga;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Tools {

    public static SlashCommandData defaultSlashCmd(Command c) {
        return Commands.slash(c.getAliases()[0], c.getHelp().split("\n")[0]);
    }

    public static Member getEffectiveMember(Guild g, String s) {
        Member m = g.getMemberById(s.replaceAll("[<@!>]", ""));
        if (m == null && User.USER_TAG.matcher(s).matches()) m = g.getMemberByTag(s);
        if (m == null) m = g.getMembersByEffectiveName(s, true).get(0);
        if (m == null) m = g.getMembersByName(s, true).get(0);
        return m;
    }

    public static void wrongUsage(SlashCommandInteractionEvent event, Command c) {
        event.replyEmbeds(new EmbedBuilder()
                .setTitle("Wrong Command Usage!")
                .setDescription(c.getHelp())
                .setColor(Color.red)
                .build()
        ).queue();
    }

    public static EmbedBuilder mangaToEmbed(Manga m) {
        if(m.summary.length() > 2000) m.summary = m.summary.substring(0, 1000) + "... [Read More!](" + m.url + ")";
        return new EmbedBuilder()
                .setAuthor(m.title, API.readmAPI+m.url)
                .setDescription(m.summary.replaceAll("SUMMARY", "").trim())
                .setImage(API.readmAPI+m.art)
                .addField("Authors",m.author,true)
                .addField("Chapters",m.chapter,true)
                .addField("Rating", m.rating, true)
                .addField("Status", m.status,true)
                .addField("Tags", String.join(", ", m.tags), false);
    }

    public static EmbedBuilder animeToEmbed(Anime a) {
        if(a==null) {
            return new EmbedBuilder()
                    .setColor(Color.red)
                    .setDescription("Could not find an anime with that search query! Please try again with a valid anime!");
        }
        EmbedBuilder e = new EmbedBuilder()
                .setAuthor("ID: " + a.malID, a.url)
                .setDescription(a.summary != null ? a.summary + " [Read More!](" + a.url + ")" : "[Read Here](" + a.url + ")")
                .setTitle(a.title)
                .addField("Type", a.type, true)
                .addField("Rating", a.rating, true)
                .addField("Score", Double.toString(a.score), true)
                .addField("Status", a.status, true)
                .addField("Rank", Integer.toString(a.rank), true)
                .addField("Episode Count", Integer.toString(a.episodeCount), true);
        if(!a.art.equals("")) e.setImage(a.art);
        return e;
    }

    public static EmbedBuilder statsToEmbed(Statistic s) {
        if(s==null) {
            return new EmbedBuilder()
                    .setColor(Color.red)
                    .setDescription("Could not find an anime with that search query! Please try again with a valid anime!");
        }
        return new EmbedBuilder()
                .addField("Watching", String.valueOf(s.watching), true)
                .addField("Completed", String.valueOf(s.completed), true)
                .addField("On Hold", String.valueOf(s.onHold), true)
                .addField("Dropped", String.valueOf(s.dropped), true)
                .addField("Plan To Watch", String.valueOf(s.planToWatch), true)
                .setFooter("Total: " + s.total);
    }

    public static EmbedBuilder characterToEmbed(Character c) {
        if(c==null) {
            return new EmbedBuilder()
                    .setColor(Color.red)
                    .setDescription("Could not find a character with that search query! Please try again with a valid character!");
        }
        return new EmbedBuilder()
                .setAuthor("ID: " + c.malID, c.url)
                .setTitle(c.name)
                .addField("Alternative Names", c.alternativeNames, false)
                .addField("Latest Anime", c.anime, false)
                .addField("Latest Manga", c.manga, false)
                .setImage(c.art);
    }

    public static EmbedBuilder pictureEmbed(String url) {
        if(url == null) {
            return new EmbedBuilder()
                    .setColor(Color.red)
                    .setDescription("Could not find any pictures related to that ID! Check for any typos.");
        }
        return new EmbedBuilder()
                .setImage(url);
    }

    public static EmbedBuilder recommendationEmbed(String animes) {
        return new EmbedBuilder()
                .setTitle("Anime Recommendations")
                .setDescription(animes);
    }

    public static EmbedBuilder newsEmbed(Article article) {
        if(article == null) {
            return new EmbedBuilder()
                    .setColor(Color.red)
                    .setDescription("Could not find any news related to that ID! Check for any typos.");
        }
        return new EmbedBuilder()
                .setAuthor(article.author,article.authorUrl)
                .setTitle(article.title, article.url)
                .setDescription(article.desc + "\n[Forum Page](" + article.forum + ")")
                .setThumbnail(article.image.equals("") ? "https://t4.ftcdn.net/jpg/01/32/42/17/360_F_132421793_uTO9DQFdFeyETi3ZPqeSy9nh4ec0shOd.jpg" : article.image)
                .addField("----------------------------------------------------------------", article.date + " | " + article.comments + " comments", false);
    }

    public static EmbedBuilder themesToEmbed(String op, String end) {
        return new EmbedBuilder()
                .setTitle("Anime Themes")
                .addField("Openings:", op, true)
                .addField("Endings:", end, true);
    }

    public static ArrayList<StringBuilder> episodeEmbeds(ArrayList<Episode> episodes) {
        ArrayList<StringBuilder> pages = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<episodes.size(); i++) {
            if(i%10 == 0 && i != 0) {
                pages.add(sb);
                sb = new StringBuilder();
            }
            sb.append("Episode #").append(i+1).append(" - [").append(episodes.get(i).title)
                    .append("](").append(episodes.get(i).url).append(")\n");
        }
        String moreUrl = "";
        if(!episodes.get(0).url.equals("")) moreUrl = episodes.get(0).url.substring(0, episodes.get(0).url.length()-2);
        if(!sb.toString().equals("")) {
            sb.append("\n> [Click for all episodes!](").append(moreUrl).append(")");
            pages.add(sb);
        }
        else
            pages.get(pages.size()-1).append("\n> [Click for all episodes!](").append(moreUrl).append(")");
        return pages;
    }

}
