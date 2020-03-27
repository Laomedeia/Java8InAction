package workTest.proxy.staticProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态代理类2（聚合方式）
 * @author neptune
 * @create 2020 03 26 6:35 下午
 */
@Slf4j
public class OrderServiceLogProxy2 implements OrderService {

    private OrderServiceImpl orderServiceImpl;

    public OrderServiceLogProxy2(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @Override
    public void reduceStock() {
        log.info("starting reduce stock ...");
        orderServiceImpl.reduceStock();
        log.info("stopping reduce stock ...");
    }
}
