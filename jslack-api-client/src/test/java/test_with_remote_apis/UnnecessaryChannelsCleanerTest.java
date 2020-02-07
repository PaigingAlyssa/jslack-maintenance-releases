package test_with_remote_apis;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.response.channels.ChannelsArchiveResponse;
import com.github.seratch.jslack.api.methods.response.conversations.ConversationsArchiveResponse;
import com.github.seratch.jslack.api.model.Channel;
import com.github.seratch.jslack.api.model.Conversation;
import com.github.seratch.jslack.api.model.ConversationType;
import config.Constants;
import config.SlackTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class UnnecessaryChannelsCleanerTest {

    static SlackTestConfig testConfig = SlackTestConfig.getInstance();
    static Slack slack = Slack.getInstance(testConfig.getConfig());

    @AfterClass
    public static void tearDown() throws InterruptedException {
        SlackTestConfig.awaitCompletion(testConfig);
    }

    @Ignore
    @Test
    public void deleteUnnecessaryPublicChannels() throws Exception {
        String token = System.getenv(Constants.SLACK_SDK_TEST_USER_TOKEN);
        for (Channel channel : slack.methods().channelsList(r -> r
                .token(token)
                .excludeArchived(true)
                .limit(1000)).getChannels()) {

            log.info(channel.toString());

            if (channel.getName().startsWith("test") && !channel.isGeneral()) {
                ChannelsArchiveResponse resp = slack.methods().channelsArchive(r -> r
                        .token(token).channel(channel.getId()));
                assertThat(resp.getError(), is(nullValue()));
            }
        }
    }

    @Ignore
    @Test
    public void deleteUnnecessaryPrivateChannels() throws Exception {
        String token = System.getenv(Constants.SLACK_SDK_TEST_USER_TOKEN);
        for (Conversation channel : slack.methods().conversationsList(r -> r
                .token(token)
                .excludeArchived(true)
                .limit(1000)
                .types(Arrays.asList(ConversationType.PRIVATE_CHANNEL))).getChannels()) {

            log.info(channel.toString());

            if ((channel.getName().startsWith("test") || channel.getName().startsWith("secret-"))
                    && !channel.isGeneral()) {
                ConversationsArchiveResponse resp = slack.methods().conversationsArchive(r -> r
                        .token(token)
                        .channel(channel.getId()));
                assertThat(resp.getError(), is(nullValue()));
            }
        }
    }

}
