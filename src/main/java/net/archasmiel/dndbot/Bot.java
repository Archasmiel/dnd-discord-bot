package net.archasmiel.dndbot;

import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.listener.MessageListener;
import net.archasmiel.dndbot.util.config.BotConfiguration;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bot starting class.
 */
public class Bot {

  public static final Logger LOGGER;

  static {
    LOGGER = LoggerFactory.getLogger(Bot.class);
  }

  /**
   * Bot starting method.
   */
  public static void main(String[] args) {

    ManaController.INSTANCE.loadData();

    DefaultShardManagerBuilder builder = DefaultShardManagerBuilder
        .createDefault(BotConfiguration.getToken());
    builder.setStatus(OnlineStatus.ONLINE);
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

}
