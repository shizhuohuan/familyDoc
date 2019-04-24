package com.es;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class ElasticSearchFactory implements PooledObjectFactory<Client> {
    private  int elasticPool_maxTotal = 10;
    private  int elasticPool_maxIdle =  5;
    private  String elasticSearch_addr ="127.0.0.1";// ConfigInfo.elasticSearch_addr;
    private  int elasticSearch_port = 9300;
    private  int elasticPool_minIdle =  1;
    private  int maxWaitMilis = 20000;

    private  static GenericObjectPool<Client> pool;// 连接池
    static
    {
        ElasticSearchFactory fa=new ElasticSearchFactory();
        fa.createElasticSearchPool();

    }
    /**
     * 创建连接池
     */
    public void createElasticSearchPool() {
        ElasticSearchFactory fac = new ElasticSearchFactory();// 创建工厂
        GenericObjectPoolConfig conf = new GenericObjectPoolConfig();// 配置文件
        conf.setMaxTotal(elasticPool_maxTotal);// 设置线程池中最大的数量
        conf.setMaxIdle(elasticPool_maxIdle);// 设置最大的空闲时间
        conf.setMinIdle(elasticPool_minIdle);// 设置最小空闲连接
        conf.setMaxWaitMillis(maxWaitMilis);// 设置最大等待时间

        try {
            //先创建三个连接
            fac.makeObject();
            fac.makeObject();
            fac.makeObject();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pool = new GenericObjectPool<Client>(fac, conf);// 创建连接池

    }

    @Override
    public PooledObject<Client> makeObject() throws Exception {
        Settings settings = Settings.builder().put("client.transport.sniff", true).build();// 自动嗅探其他集群的ip
        InetSocketTransportAddress master = null;
        master = new InetSocketTransportAddress(InetAddress.getByName(elasticSearch_addr), elasticSearch_port);
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(master);
        System.out.println("太棒了,对象创建成功了！");
        return new DefaultPooledObject<Client>(client);
    }

    public static GenericObjectPool<Client> getPool() {
        return pool;
    }

    //销毁
    @Override
    public void destroyObject(PooledObject<Client> p) throws Exception {
        p.getObject().close();
    }
    @Override
    public boolean validateObject(PooledObject<Client> p) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void activateObject(PooledObject<Client> p) throws Exception {

    }
    @Override
    public void passivateObject(PooledObject<Client> p) throws Exception {
        System.out.println("passivate Object"+p.toString());

    }
}
