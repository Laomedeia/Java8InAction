package juejin.netty.netty.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import juejin.netty.netty.Attributes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author neptune
 * @create 2018 11 26 10:35 AM
 */
public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> groupIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(UserSession session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {

        return channel.hasAttr(Attributes.SESSION);
    }

    public static UserSession getSession(Channel channel) {

        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return userIdChannelMap.get(userId);
    }

    public static void initChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdChannelMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return  groupIdChannelMap.get(groupId);
    }

}
