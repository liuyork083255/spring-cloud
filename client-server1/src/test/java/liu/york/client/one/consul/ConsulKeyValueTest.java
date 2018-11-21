package liu.york.client.one.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.ecwid.consul.v1.kv.model.PutParams;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ConsulKeyValueTest {

    ConsulClient consulClient;

    @Before
    public void init(){
        consulClient = new ConsulClient("192.168.159.137",8500);
    }

    @Test
    public void fun1() throws InterruptedException {

        PutParams params = new PutParams();

        params.setCas(5000L);

        Response<Boolean> booleanResponse = consulClient.setKVValue("key1", "value12");
        System.out.println(booleanResponse.getValue());


        while (true){
            Response<GetValue> key1 = consulClient.getKVValue("key1");
            GetValue value = key1.getValue();
            System.out.println(value.getValue());


            TimeUnit.SECONDS.sleep(1);
        }

    }

    @Test
    public void fun2() throws InterruptedException {
        ConsulClient consulClient = new ConsulClient("localhost", 8500);	// 创建与Consul的连接
        CheckTtl checkTtl = new CheckTtl("checkId", consulClient); // session的健康检查，用来清理失效session占用的锁
        Lock lock = new Lock(consulClient, "lockKey", checkTtl);
        try {
            // 获取分布式互斥锁
            // 参数含义：是否使用阻塞模式、每次尝试获取锁的间隔500ms、尝试n次
            if (lock.lock(true, 500L, null)) {
                // TODO 处理业务逻辑
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}