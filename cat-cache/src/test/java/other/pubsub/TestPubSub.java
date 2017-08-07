package other.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by chenqinhao on 2017/7/16.
 */
public class TestPubSub extends JedisPubSub{

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("onMessage: channel[" + channel + "], message[" + message + "]");
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage: pattern[" + pattern + "] channel[" + channel + "], message[" + message + "]");
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("onSubscribe: channel[" + channel + "], subscribedChannels[" + subscribedChannels + "]");
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("onUnsubscribe: channel[" + channel + "], subscribedChannels[" + subscribedChannels + "]");
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPUnsubscribe: pattern[" + pattern + "] subscribedChannels[" + subscribedChannels + "]");
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe: pattern[" + pattern + "] subscribedChannels[" + subscribedChannels + "]");
    }

    @Override
    public void onPong(String pattern) {
        System.out.println("onPSubscribe: pattern[" + pattern + "]");
    }

    public static void main(String[] args) {
        try(Jedis js = new Jedis("localhost", 6379, 0)){

            TestPubSub sp = new TestPubSub();
            sp.proceed(js.getClient(), "news share", "news blogs");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
