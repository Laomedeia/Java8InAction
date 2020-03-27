package workTest.proxy.staticProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态代理类3（聚合方式实现静态代理--日志记录功能叠加改造）
 * @author neptune
 * @create 2020 03 26 6:35 下午
 */
@Slf4j
public class OrderServiceLogProxy3 implements OrderService {

    private OrderService orderService;

    public OrderServiceLogProxy3(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void reduceStock() {
        log.info("starting reduce stock ...");
        orderService.reduceStock();
        log.info("stopping reduce stock ...");
    }
}
