package workTest.proxy.cglibProxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 基于Cglib方式实现动态代理-日志功能
 * 不同于 JDK 动态代理。它是针对类实现代理的，类不用实现接口，CGlib对目标类产生一个子类，通过方法拦截技术拦截所有的方法调用
 *
 * @author neptune
 * @create 2020 03 26 10:50 下午
 */
@Slf4j
public class DynamicCglibLogProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxyObj(Class clazz) {
        //设置父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        enhancer.setUseCache(false);
        return enhancer.create();
    }

    /**
     * 拦截所有目标类的方法调用
     *
     * @param o           目标对象
     * @param method      目标方法
     * @param args        方法参数
     * @param methodProxy 代理类实例
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("这里是日志记录切面，日志开始……");
        //代理类对象实例调用父类方法
        methodProxy.invokeSuper(o, args);
        log.info("这里是日志记录切面，日志结束……");
        return null;
    }

}
