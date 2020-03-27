package workTest.reactive;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Guava EventBus 响应式编程
 * @author neptune
 * @create 2019 09 17 11:25 上午
 */
public class GuavaEventBus {

    @Subscribe
    public void sendMessageByEmail(String message){
        System.out.println("邮件发送一条消息：" + message);
    }

    @Subscribe
    public void sendMessageByPhone(String message){
        System.out.println("手机发送一条消息：" + message);
    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new GuavaEventBus());
        eventBus.post("Hi, All");
    }

}
