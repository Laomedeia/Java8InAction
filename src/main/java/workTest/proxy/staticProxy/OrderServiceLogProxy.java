package workTest.proxy.staticProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态代理类（继承方式）
 * @author neptune
 * @create 2020 03 26 6:35 下午
 */
@Slf4j
public class OrderServiceLogProxy extends OrderServiceImpl {
    @Override
    public void reduceStock() {
        log.info("starting reduce stock ...");
        super.reduceStock();
        log.info("stopping reduce stock ...");
    }
}
