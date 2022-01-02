package lollipop;

import lollipop.listeners.LollipopReaction;
import lollipop.listeners.PageListener;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {
        DefaultShardManagerBuilder lollipop = DefaultShardManagerBuilder.createDefault(Secret.TOKEN)
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.watching("anime | l!help"))
                .setShardsTotal(4)
                .addEventListeners(new Listener())
                .addEventListeners(new PageListener())
                .addEventListeners(new LollipopReaction());
        ShardManager bot = lollipop.build();
    }

}
