package net.archasmiel.dndbot;

import java.io.InputStream;
import java.sql.Connection;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import net.archasmiel.dndbot.database.ManaController;
import net.archasmiel.dndbot.listener.MessageListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Bot {

  public static final Logger logger = Logger.getGlobal();
  private final ShardManager shardManager;
  public static Connection connection;

  public Bot(String token) throws LoginException {
    DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
    builder.setStatus(OnlineStatus.ONLINE);
    builder.setActivity(Activity.watching("DnD 3.5e"));
    builder.enableIntents(
        GatewayIntent.GUILD_MEMBERS,
        GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.MESSAGE_CONTENT
    );
    builder.setMemberCachePolicy(MemberCachePolicy.ALL);
    builder.setChunkingFilter(ChunkingFilter.ALL);
    shardManager = builder.build();

    shardManager.addEventListener(new MessageListener());
  }

  public static void main(String[] args) {
    try {
      ManaController.readUsers();
      Bot bot = new Bot(getConfig("token"));
    } catch (LoginException e) {
      logger.info("Bot login exception!");
    }
  }

  public ShardManager getShardManager() {
    return shardManager;
  }

  public static String getConfig(String param) {
    InputStream is = Bot.class.getResourceAsStream("/config.json");
    if (is == null) throw new IllegalStateException("Token read exception!");

    return new JSONObject(new JSONTokener(is)).getString(param);
  }

}
