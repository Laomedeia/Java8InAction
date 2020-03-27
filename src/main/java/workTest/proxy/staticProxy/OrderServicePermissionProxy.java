package workTest.proxy.staticProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态代理类3（聚合方式实现静态代理--日志记录功能叠加改造）
 * @author neptune
 * @create 2020 03 26 6:35 下午
 */
@Slf4j
public class OrderServicePermissionProxy implements OrderService {

    private OrderService orderService;

    public OrderServicePermissionProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void reduceStock() {
        log.info("权限验证开始 ...");
        orderService.reduceStock();
        log.info("权限验证结束 ...");
    }
}
