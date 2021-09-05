package com.zookeeper.zookeeperdemo.curator;

import com.zookeeper.zookeeperdemo.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CuratorZookeeperTest extends BaseTest {

    @Autowired
    private CuratorFramework zkClient;

    @Test
    public void testCreateZkNode() throws Exception {
        String path = "/app1";
        byte[] data = {1};
        zkClient.create().withMode(CreateMode.PERSISTENT)
                .forPath(path,data);

        byte[] result = zkClient.getData().forPath(path);
        log.info("result:{}",result);
    }
}
