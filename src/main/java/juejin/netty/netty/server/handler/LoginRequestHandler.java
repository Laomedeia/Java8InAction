package juejin.netty.netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import juejin.netty.netty.IDUtil;
import juejin.netty.netty.protocol.packet.request.LoginRequestPacket;
import juejin.netty.netty.protocol.packet.response.LoginResponsePacket;
import juejin.netty.netty.session.SessionUtil;
import juejin.netty.netty.session.UserSession;

import java.util.Date;

/**
 * Server 端收到登录 request 后处理的 handler
 * @author neptune
 * @create 2018 11 22 6:15 PM
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    // 构造单例
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        System.out.println(new Date() + ": 收到客户端登录请求……");
        // 对于耗时的操作，不要直接在 NIO 线程里做，比如，不要在 channelRead0() 方法里做一些访问数据库或者网络相关的逻辑，要扔到自定义线程池里面去做，
        // 然后要注意这个时候，writeAndFlush() 的执行是异步的，需要通过添加监听回调的方式 (addListener) 来判断是否执行完毕，进而进行延时的统计。
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = IDUtil.randomId();
            // 添加登录成功标志
//            LoginUtil.markAsLogin(ctx.channel());
//            System.out.println(new Date() + ": 登录成功!");
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new UserSession(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应 (注意ctx.channel().writeAndFlush() 和 ctx.writeAndFlush() 事件传播路径区别)
        // ctx.writeAndFlush() 是从 pipeline 链中的当前节点开始往前找到第一个 outBound 类型的 handler 把对象往前进行传播，如果这个对象确认不需要经过其他 outBound 类型的 handler 处理，就使用这个方法。
        // ctx.channel().writeAndFlush() 是从 pipeline 链中的最后一个 outBound 类型的 handler 开始，把对象往前进行传播，如果你确认当前创建的对象需要经过后面的 outBound 类型的 handler，那么就调用此方法。
        ctx.writeAndFlush(loginResponsePacket);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
