package workTest.proxy.dynamicProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理实现，必须实现InvocationHandler接口
 * InvocationHandler可以理解为事务处理器，所有切面级别的逻辑都在此完成
 *
 * @author neptune
 * @create 2020 03 26 10:39 下午
 */
@Slf4j
public class DynamicPermissionProxy implements InvocationHandler {

    //需要代理的对象类
    private Object target;

    public DynamicPermissionProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("这里是权限认证切面，日志开始……");
        //使用方法的反射
        Object invoke = method.invoke(target, args);
        log.info("这里是权限认证切面，日志结束……");
        return invoke;
    }
}
