package workTest.proxy;

import workTest.proxy.cglibProxy.DynamicCglibLogProxy;
import workTest.proxy.dynamicProxy.DynamicLogProxy;
import workTest.proxy.dynamicProxy.DynamicPermissionProxy;
import workTest.proxy.staticProxy.OrderService;
import workTest.proxy.staticProxy.OrderServiceImpl;
import workTest.proxy.staticProxy.OrderServiceLogProxy2;
import workTest.proxy.staticProxy.OrderServicePermissionProxy;

import java.lang.reflect.Proxy;

/**
 * 静态/动态/CGLib 代理测试
 *
 * JDK与Cglib动态代理对比？
 * 1、JDK动态代理只能代理实现了接口的类，没有实现接口的类不能实现JDK的动态代理；
 * 2、Cglib动态代理是针对类实现代理的，运行时动态生成被代理类的子类拦截父类方法调用，因此不能代理声明为final类型的类和方法；
 *
 * 动态代理和静态代理的区别？
 * 1、静态代理在代理前就知道要代理的是哪个对象，而动态代理是运行时才知道；
 * 2、静态代理一般只能代理一个类，而动态代理能代理实现了接口的多个类；
 *
 * Spring如何选择两种代理模式的？
 * 1、如果目标对象实现了接口，则默认采用JDK动态代理；
 * 2、如果目标对象没有实现接口，则使用Cglib代理；
 * 3、如果目标对象实现了接口，但强制使用了Cglib，则使用Cglib进行代理
 *
 * Cglib包可以直接通过引入 spring-core 来引入，而本项目则直接引入了 cglib 的依赖
 * <dependency>
 * 	<groupId>org.springframework</groupId>
 * 	<artifactId>spring-core</artifactId>
 * 	<version>4.1.0.RELEASE</version>
 * </dependency>
 *
 * @author neptune
 * @create 2020 03 26 9:59 下午
 */
public class TestOrderServiceProxy {
    public static void main(String[] args) {
        // 测试静态代理类 1
//        OrderServiceLogProxy orderServiceLogProxy = new OrderServiceLogProxy();
//        orderServiceLogProxy.reduceStock();

        // 测试静态代理类 2
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        OrderServiceLogProxy2 proxy2 = new OrderServiceLogProxy2(orderService);
//        proxy2.reduceStock();

        // 测试静态代理类 3
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        OrderServiceLogProxy2 logProxy2 = new OrderServiceLogProxy2(orderService);
//        OrderServicePermissionProxy permissionProxy = new OrderServicePermissionProxy(logProxy2);
//        permissionProxy.reduceStock();

        // 测试动态代理
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        Class<?> clazz = orderService.getClass();
//        DynamicLogProxy logProxyHandler = new DynamicLogProxy(orderService);
//            //通过Proxy.newProxyInstance(类加载器, 接口s, 事务处理器Handler) 加载动态代理
//        OrderService os = (OrderService) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), logProxyHandler);
//        DynamicPermissionProxy dynamicPermissionHandler = new DynamicPermissionProxy(os);
//        OrderService os2 = (OrderService) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), dynamicPermissionHandler);
//        os2.reduceStock();

        // 测试 cglib 代理
        DynamicCglibLogProxy dynamicCglibLogProxy = new DynamicCglibLogProxy();
        OrderServiceImpl orderService = (OrderServiceImpl)dynamicCglibLogProxy.getProxyObj(OrderServiceImpl.class);
        orderService.reduceStock();


    }

}
