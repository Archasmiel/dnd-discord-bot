package net.archasmiel.dndbot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.listener.MessageListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Bot {

  public static void main(String[] args) throws IOException {
    ManaController.readUsers();

    DefaultShardManagerBuilder builder = DefaultShardManagerBuilder
        .createDefault(getToken());
    builder.setStatus(OnlineStatus.ONLINE);
    builder.setActivity(Activity.watching("DnD 3.5e"));
    builder.enableIntents(
        GatewayIntent.GUILD_MEMBERS,
        GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.MESSAGE_CONTENT
    );
    builder.setMemberCachePolicy(MemberCachePolicy.ALL);
    builder.setChunkingFilter(ChunkingFilter.ALL);

    ShardManager shardManager = builder.build();
    shardManager.addEventListener(new MessageListener());
  }

  public static String getToken() throws IOException {
    InputStream is = Bot.class.getResourceAsStream("/token");
    if (is == null) throw new IllegalStateException("Token read exception!");
    return new String(is.readAllBytes(), StandardCharsets.UTF_8);
  }

}
