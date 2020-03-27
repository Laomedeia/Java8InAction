package workTest.proxy.staticProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author neptune
 * @create 2020 03 26 6:28 下午
 */
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public void reduceStock() {
        try {
            log.info("减去库存中");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
