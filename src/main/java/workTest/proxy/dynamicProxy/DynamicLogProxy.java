package workTest.proxy.dynamicProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理实现，必须实现InvocationHandler接口
 * InvocationHandler可以理解为事务处理器，所有切面级别的逻辑都在此完成
 *
 * 使用JDK动态代理类基本步骤：
 * 1、编写需要被代理的类和接口（我这里就是OrderServiceImpl、OrderService）；
 * 2、编写代理类（例如我这里的DynamicLogProxy），需要实现InvocationHandler接口，重写invoke方法；
 * 3、使用Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)动态创建代理类对象，通过代理类对象调用业务方法。
 *
 * @author neptune
 * @create 2020 03 26 10:39 下午
 */
@Slf4j
public class DynamicLogProxy implements InvocationHandler {

    //需要代理的对象类
    private Object target;

    public DynamicLogProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("这里是日志记录切面，日志开始……");
        //使用方法的反射
        Object invoke = method.invoke(target, args);
        log.info("这里是日志记录切面，日志结束……");
        return invoke;
    }
}
